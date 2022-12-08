package ru.kpfu.itis.servlets;

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

@WebServlet(urlPatterns = {"/mainPage"})
public class MainPageServlet extends HttpServlet {
    private AdvertService advertService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.advertService = (AdvertService) getServletContext().getAttribute("advertService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Advert> advertList = advertService.findAllActiveAdverts();
        List<Advert> listBySearch;
        List<Advert> listByCity;
        String city = req.getParameter("city");
        String searchInput = req.getParameter("searchInput");

        if (searchInput != null && !searchInput.isEmpty()) {
            listBySearch = advertService.getAllAdvertsBySearchInput(searchInput);
            req.setAttribute("listBySearch", listBySearch);
        }
        if (city != null) {
            listByCity = advertService.getAllAdvertsByCity(city);
            req.setAttribute("listByCity", listByCity);
        }

        req.setAttribute("adverts", advertList);
        req.getRequestDispatcher("/mainPage.jsp").forward(req, resp);
    }
}
