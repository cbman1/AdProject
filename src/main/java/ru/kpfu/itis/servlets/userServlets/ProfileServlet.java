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


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final static String STORAGE_PATH = "C:/Users/79174/IdeaProjects/AdProject/storage/";
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
        User thisUser = (User) req.getSession(false).getAttribute("user");
        User profileUser = userService.getUserById(Long.parseLong(req.getParameter("id")));
        String avatarUuid = userService.getAvatarUUIDById(profileUser);

        req.setAttribute("profileUser", profileUser);
        req.setAttribute("thisUser", thisUser);

        List<Advert> allActiveAdverts = advertService.getAllActiveAdvertsUser(profileUser);
        List<Advert> allSoldAdverts = advertService.getAllSoldAdvertsUser(profileUser);

        req.setAttribute("activeAdverts", allActiveAdverts);
        req.setAttribute("soldAdverts", allSoldAdverts);
        req.setAttribute("avatar", avatarUuid);
        req.setAttribute("storagePath", STORAGE_PATH);

        req.getRequestDispatcher("/jsp/userPages/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
