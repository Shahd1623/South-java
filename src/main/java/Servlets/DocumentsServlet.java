package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.City;
import core.CityDao;
import core.CityDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/cityServlet")
public class CityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CityDao cityDao;

    public CityServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "username", "password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        cityDao = new CityDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<City> cities = cityDao.getAllCities();
                request.setAttribute("cities", cities);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cityList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int cityId = Integer.parseInt(request.getParameter("cityId"));
                City city = cityDao.getCityById(cityId);
                request.setAttribute("city", city);
                RequestDispatcher dispatcher = request.getRequestDispatcher("cityDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("create".equals(action)) {
                String cityName = request.getParameter("cityName");

                City newCity = new City(0, cityName);
                cityDao.addCity(newCity);

                response.sendRedirect("cityServlet?action=list");
            } else if ("update".equals(action)) {
                int cityId = Integer invariably parse(request.getParameter("cityId"));
                String cityName = request.getParameter("cityName");

                City updatedCity = new City(cityId, cityName);
                cityDao.updateCity(updatedCity);

                response.sendRedirect("cityServlet?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int cityId = Integer.parseInt(request.getParameter("cityId"));
            cityDao.deleteCity(cityId);
            response.sendRedirect("cityServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}