package ru.kpfu.itis.servlets.advertServlets;

import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.security.exceptions.NotAllowedException;
import ru.kpfu.itis.services.AdvertService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.NotActiveException;
import java.util.List;

@WebServlet(urlPatterns = {"/advert", "/advert/sold", "/advert/addFavourite", "/advert/removeFavourite", "/advert/returnAdvert"})
public class AdvertServlet extends HttpServlet {
    private UserService userService;
    private AdvertService advertService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserService) getServletContext().getAttribute("userService");
        this.advertService = (AdvertService) getServletContext().getAttribute("advertService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/advertPages/advertPage.jsp");
        Long idAdvert = Long.parseLong(req.getParameter("id"));
        Advert thisAdvert = advertService.getAdvertById(idAdvert);
        User authorAdvert = userService.getUserById(thisAdvert.getAuthorId());
        User thisUser = (User) req.getSession(false).getAttribute("user");
        Integer countFavourite = advertService.getCountFavouriteAdvert(thisAdvert);
        boolean flagCheckAuthor = false;
        List<Advert> listFavoriteAds = null;

        if (thisUser != null) {
            flagCheckAuthor = thisUser.getId().equals(authorAdvert.getId());
            listFavoriteAds = advertService.getFavouriteAdvertsUser(thisUser);
        }

        req.setAttribute("advert", thisAdvert);
        req.setAttribute("author", authorAdvert);
        req.setAttribute("user", thisUser);
        req.setAttribute("listFavouriteAds", listFavoriteAds);
        req.setAttribute("countFavourite", countFavourite);

        if (req.getRequestURI().equals("/advert/removeFavourite")) {
            advertService.removeAdvertFromFavourite(thisAdvert, thisUser);
            resp.sendRedirect("/advert?id=" + idAdvert);
        }
        else if (req.getRequestURI().equals("/advert/returnAdvert") && flagCheckAuthor) {
            advertService.returnInSellSoldAdvert(thisAdvert);
            resp.sendRedirect("/advert?id=" + idAdvert);
        }
        else if (req.getRequestURI().equals("/advert/addFavourite")) {
            advertService.addAdvertToFavourite(thisUser, thisAdvert);
            resp.sendRedirect("/advert?id=" + idAdvert);
        }
        else if (req.getRequestURI().equals("/advert/sold") && flagCheckAuthor) {
            advertService.soldAdvert(thisAdvert);
            resp.sendRedirect("/advert?id=" + idAdvert);
        } else if (!flagCheckAuthor && !req.getRequestURI().equals("/advert")) {
            throw new NotAllowedException();
        }
        else {
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
