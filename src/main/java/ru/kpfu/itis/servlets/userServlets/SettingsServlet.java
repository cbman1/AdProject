package ru.kpfu.itis.servlets.userServlets;


//import ru.kpfu.itis.services.UserService;

import ru.kpfu.itis.models.User;
import ru.kpfu.itis.security.exceptions.NotAllowedException;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile/settings")
public class SettingsServlet extends HttpServlet {
    private UserService userService;
    private static User user;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = (User) req.getSession(false).getAttribute("user");
        User profileUser = userService.getUserById(Long.parseLong(req.getParameter("id")));
        boolean flagCheckAllow = user != null && user.getId().equals(profileUser.getId());

        if (!flagCheckAllow) {
            throw new NotAllowedException();
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/userPages/settingsUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService.updateInformationUser(user, req.getParameter("city"), req.getParameter("address"));
        resp.sendRedirect("/profile?id=" + user.getId());
    }
}
