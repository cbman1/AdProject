package ru.kpfu.itis.servlets.advertServlets;


import ru.kpfu.itis.dao.AdvertDao;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.AdvertService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAdvert")
public class AddAdvertServlet extends HttpServlet {
    private AdvertService advertService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.advertService = (AdvertService) getServletContext().getAttribute("advertService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/advertPages/addAdvert.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdvertDao advertDao = AdvertDao.builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .price(Integer.parseInt(req.getParameter("price")))
                .category(req.getParameter("category"))
                .build();
        User user = (User) req.getSession(false).getAttribute("user");
        advertService.addAdvert(advertDao, user);
        resp.sendRedirect("/mainPage");
    }
}
