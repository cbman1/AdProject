package ru.kpfu.itis.servlets.advertServlets;


import ru.kpfu.itis.dao.EditAdvertDao;
import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.security.exceptions.NotAllowedException;
import ru.kpfu.itis.services.AdvertService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/advert/editAdvert")
public class EditAdvertServlet extends HttpServlet {
    private static Long id;
    private AdvertService advertService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.advertService = (AdvertService) getServletContext().getAttribute("advertService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idEditAdvert = Long.parseLong(req.getParameter("id"));
        Advert advert = advertService.getAdvertById(idEditAdvert);

        req.setAttribute("advert", advert);

        User thisUser = (User) req.getSession(false).getAttribute("user");
        id = Long.parseLong(req.getParameter("id"));


        if (!thisUser.getId().equals(advertService.getAdvertById(id).getAuthorId())) {
            throw new NotAllowedException();
        }

        req.getRequestDispatcher("/jsp/advertPages/editAdvertPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditAdvertDao editAdvertDao = EditAdvertDao.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();

        advertService.updateInformationAdvert(id, editAdvertDao);
        resp.sendRedirect("/advert?id=" + id);
    }
}
