package ru.kpfu.itis.servlets.filesServlets;


import ru.kpfu.itis.dao.FileDao;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.FilesService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/uploadAvatar")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    private FilesService filesService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.filesService = (FilesService) getServletContext().getAttribute("filesService");
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part filePart = req.getPart("file");

        User user = (User) req.getSession(false).getAttribute("user");
        String type = null;

        if (req.getRequestURI().equals("/uploadAvatar") && !userService.getAvatarUUIDById(user).equals("default.png")) {
            type = "tempAvatar";
        } else if (req.getRequestURI().equals("/uploadAvatar") && userService.getAvatarUUIDById(user).equals("default.png")) {
            type = "avatar";
        }

        FileDao uploadedFileInfo = FileDao.builder()
                .size(filePart.getSize())
                .mimeType(filePart.getContentType())
                .fileName(filePart.getSubmittedFileName())
                .fileStream(filePart.getInputStream())
                .loadAccount(user.getId())
                .type(type)
                .build();

        filesService.upload(uploadedFileInfo);
        resp.sendRedirect("/addAvatar?id=" + user.getId());
    }
}
