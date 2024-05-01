package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import core.PropertyImages;
import core.PropertyImagesDao;
import core.PropertyImagesDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/propertyImagesServlet")
public class PropertyImagesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyImagesDao propertyImagesDao;

    public PropertyImagesServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "your_user", "your_password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        propertyImagesDao = new PropertyImagesDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<PropertyImages> images = propertyImagesDao.getAllImages();
                request.setAttribute("propertyImagesList", images);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyImagesList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int imageId = Integer.parseInt(request.getParameter("imageId"));
                PropertyImages image = propertyImagesDao.getImageById(imageId);
                request.setAttribute("propertyImage", image);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyImage.jsp");
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
                String imageUrl = request.getParameter("imageUrl");

                PropertyImages newImage = new PropertyImages(0, listingId, imageUrl);
                propertyImagesDao.addImage(newImage);

                response.sendRedirect("propertyImagesServlet?action=list");
            } else if ("update".equals(action)) {
                int imageId = Integer.parseInt(request.getParameter("imageId"));
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                String imageUrl = request.getParameter("imageUrl");

                PropertyImages updatedImage = new PropertyImages(imageId, listingId, imageUrl);
                propertyImagesDao.updateImage(updatedImage);

                response.sendRedirect("propertyImagesServlet?action=list");
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
            int imageId = Integer.parseInt(request.getParameter("imageId"));
            propertyImagesDao.deleteImage(imageId);
            response.sendRedirect("propertyImagesServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
