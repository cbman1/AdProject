package ru.kpfu.itis.servlets.filesServlets;


import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.FilesService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addAvatar")
public class AddAvatarUserServlet extends HttpServlet {
    private FilesService filesService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.filesService = (FilesService) getServletContext().getAttribute("filesService");
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession(false).getAttribute("user");

        if (!userService.getAvatarUUIDById(user).equals("default.png")) {
            filesService.deleteOldAvatar(user);
        }

        FileInfo fileInfo = filesService.getFileInfoAvatarById(user);

        filesService.addAvatarUser(fileInfo, user);
        userService.setAvatarUuid(fileInfo, user);

        resp.sendRedirect("/profile/settings?id=" + user.getId());
    }
}
