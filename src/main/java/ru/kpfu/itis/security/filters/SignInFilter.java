package ru.kpfu.itis.security.filters;


import ru.kpfu.itis.models.User;
import ru.kpfu.itis.security.AuthenticationManager;
import ru.kpfu.itis.security.validators.UserValidator;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.utils.UserPasswordHasher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/signIn")
public class SignInFilter implements Filter {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.authenticationManager = (AuthenticationManager) filterConfig.getServletContext().getAttribute("authenticateManager");
        this.userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equals("POST")) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (authenticationManager.authenticate(email, UserPasswordHasher.getHashedPassword(password))) {
                HttpSession session = req.getSession(true);
                session.setAttribute("authenticated", true);
                session.setAttribute("user", userService.getUserByEmail(request.getParameter("email")).get());
                resp.sendRedirect("/mainPage");
            } else {
                resp.sendRedirect("/signIn" + "?error");
            }
        } else {
            chain.doFilter(request, response);
        }

    }
}
