package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for handling property reviews.
 */
@WebServlet("/propertyReviewServlet")
public class PropertyReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PropertyReviewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String message = "Served at: " + request.getContextPath();
            response.setContentType("text/plain");
            response.getWriter().write(message);
        } catch (IOException e) {
            throw new ServletException("Error handling GET request.", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if (action == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is required.");
                return;
            }

            switch (action) {
                case "addReview":
                    // Placeholder for adding a property review
                    handleAddReview(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                    break;
            }
        } catch (IOException e) {
            throw new ServletException("Error handling POST request.", e);
        }
    }

    private void handleAddReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logic to handle adding a property review
        // This can include processing form data, interacting with a database, etc.
        response.getWriter().write("Add review feature not implemented yet.");
    }
}