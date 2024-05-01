package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import core.PropertyReviews;
import core.PropertyReviewsDao;
import core.PropertyReviewsDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/propertyReviewsServlet")
public class PropertyReviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyReviewsDao propertyReviewsDao;

    public PropertyReviewsServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "your_user", "your_password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        propertyReviewsDao = new PropertyReviewsDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<PropertyReviews> reviews = propertyReviewsDao.getAllReviews();
                request.setAttribute("propertyReviewsList", reviews);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyReviewsList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int reviewId = Integer.parseInt(request.getParameter("reviewId"));
                PropertyReviews review = propertyReviewsDao.getReviewById(reviewId);
                request.setAttribute("propertyReview", review);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyReview.jsp");
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
                int userId = Integer.parseInt(request.getParameter("userId"));
                Float rating = Float.parseFloat(request.getParameter("rating"));
                String reviewText = request.getParameter("reviewText");
                Date reviewDate = Date.valueOf(request.getParameter("reviewDate"));

                PropertyReviews newReview = new PropertyReviews(0, listingId, userId, rating, reviewText, reviewDate);
                propertyReviewsDao.addReview(newReview);

                response.sendRedirect("propertyReviewsServlet?action=list");
            } else if ("update".equals(action)) {
                int reviewId = Integer.parseInt(request.getParameter("reviewId"));
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                Float rating = Float.parseFloat(request.getParameter("rating"));
                String reviewText = request.getParameter("reviewText");
                Date reviewDate = Date.valueOf(request.getParameter("reviewDate"));

                PropertyReviews updatedReview = new PropertyReviews(reviewId, listingId, userId, rating, reviewText, reviewDate);
                propertyReviewsDao.updateReview(updatedReview);

                response.sendRedirect("propertyReviewsServlet?action=list");
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
            int reviewId = Integer.parseInt(request.getParameter("reviewId"));
            propertyReviewsDao.deleteReview(reviewId);
            response.sendRedirect("propertyReviewsServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
