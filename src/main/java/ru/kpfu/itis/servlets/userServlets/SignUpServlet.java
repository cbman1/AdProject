package ru.kpfu.itis.servlets.userServlets;



import ru.kpfu.itis.dao.SignUpDao;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/userPages/signUp.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String body = request.getReader().readLine();
//        SignUpDto signUpDto = objectMapper.readValue(body, SignUpDto.class);
        try {
            signUpUser(request);
            response.sendRedirect("/signIn?message=Successful registration");
        } catch (IllegalArgumentException ex) {
            response.sendRedirect("/signUp?message=" + ex.getMessage());
        }
    }

    private void signUpUser(HttpServletRequest request) {
        if (request.getParameter("policy") == null) {
            throw new IllegalArgumentException("Privacy policy wasn't agreed.");
        }


        SignUpDao signUpDto = SignUpDao.builder()
                .firstName(request.getParameter("firstName"))
                .lastName(request.getParameter("lastName"))
                .email(request.getParameter("email"))
                .password(request.getParameter("password"))
                .phoneNumber(Long.parseLong(request.getParameter("phoneNumber")))
                .build();

        if (userService.isRegisterUser(signUpDto)) {
            throw new IllegalArgumentException("This email is already registered");
        }


        userService.signUp(signUpDto);
    }
}
