package ru.kpfu.itis.security.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = {"/addAdvert", "/favourites", "/advert/*", "/profile/*", "/uploadAvatar", "/addAvatar"})
public class AuthenticationFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/profile", "/advert")));
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (req.getRequestURI().equals("/signIn") || allowedPath) {
            chain.doFilter(req, resp);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session != null) {
            Boolean authenticated = (Boolean) session.getAttribute("authenticated");
            if (authenticated != null && authenticated) {
                chain.doFilter(req, resp);
                return;
            }
        }
        resp.sendRedirect("/signIn");
    }
}
