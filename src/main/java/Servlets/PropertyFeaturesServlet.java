package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.PropertyFeatures;
import core.PropertyFeaturesDao;
import core.PropertyFeaturesDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/propertyFeaturesServlet")
public class PropertyFeaturesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyFeaturesDao propertyFeaturesDao;

    public PropertyFeaturesServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "username", "password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        propertyFeaturesDao = new PropertyFeaturesDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<PropertyFeatures> features = propertyFeaturesDao.getAllPropertyFeatures();
                request.setAttribute("features", features);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyFeaturesList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int featureId = Integer.parseInt(request.getParameter("featureId"));
                PropertyFeatures feature = propertyFeaturesDao.getPropertyFeaturesById(featureId);
                request.setAttribute("feature", feature);
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
                propertyFeaturesDao.addPropertyFeatures(newFeature);

                response.sendRedirect("propertyFeaturesServlet?action=list");
            } else if ("update".equals(action)) {
                int featureId = Integer.parseInt(request.getParameter("featureId"));
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                String featureName = request.getParameter("featureName");
                String featureValue = request.getparameter("featureValue");

                PropertyFeatures updatedFeature = new PropertyFeatures(featureId, listingId, featureName, featureValue);
                propertyFeaturesDao.updatePropertyFeatures(updatedFeature);

                response.sendRedirect("propertyFeaturesServlet?action=list");
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
            int featureId = Integer parseInt(request.getParameter("featureId"));
            propertyFeaturesDao.deletePropertyFeatures(featureId);
            response.sendRedirect("propertyFeaturesServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}