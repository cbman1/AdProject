package ru.kpfu.itis.servlets.userServlets;

import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.AdvertService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/favourites")
public class FavouriteServlet extends HttpServlet {
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
        User user = (User) req.getSession(false).getAttribute("user");
        List<Advert> favouriteAdvertList = advertService.getFavouriteAdvertsUser(user);

        req.setAttribute("adverts", favouriteAdvertList);

        req.getRequestDispatcher("/jsp/userPages/favourites.jsp").forward(req, resp);
    }

}
