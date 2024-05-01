package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;
import core.PropertyFeatures;
import core.PropertyFeaturesDao;
import core.PropertyFeaturesDaoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/PropertyFeatureServlet")
public class PropertyFeaturesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyFeaturesDao propertyFeaturesDao;

    public PropertyFeaturesServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/southdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        propertyFeaturesDao = new PropertyFeaturesDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<PropertyFeatures> features = propertyFeaturesDao.getAllFeatures();
                request.setAttribute("propertyFeaturesList", features);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyFeaturesList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int featureId = Integer.parseInt(request.getParameter("featureId"));
                PropertyFeatures feature = propertyFeaturesDao.getFeatureById(featureId);
                request.setAttribute("propertyFeature", feature);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyFeature.jsp");
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
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                String featureName = request.getParameter("featureName");
                String featureValue = request.getParameter("featureValue");

                PropertyFeatures newFeature = new PropertyFeatures(0, listingId, featureName, featureValue);
                propertyFeaturesDao.addFeature(newFeature);

                response.sendRedirect("propertyFeatureServlet?action=list");
            } else if ("update".equals(action)) {
                int featureId = Integer.parseInt(request.getParameter("featureId"));
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                String featureName = request.getParameter("featureName");
                String featureValue = request.getParameter("featureValue");

                PropertyFeatures updatedFeature = new PropertyFeatures(featureId, listingId, featureName, featureValue);
                propertyFeaturesDao.updateFeature(updatedFeature);

                response.sendRedirect("propertyFeatureServlet?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action.");
            }
        } catch (SQLException | NumberFormatException e) {
            throw a new ServletException("Error processing request.", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int featureId = Integer.parseInt(request.getParameter("featureId"));
            propertyFeaturesDao.deleteFeature(featureId);
            response.sendRedirect("propertyFeatureServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw a new ServletException("Error processing request.", e);
        }
    }
}
