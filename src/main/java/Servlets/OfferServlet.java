package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;
import core.Offer;
import core.OfferDao;
import core.OfferDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/offerServlet")
public class OfferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OfferDao offerDao;

    public OfferServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize DAO with appropriate database connection
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/southdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        offerDao = new OfferDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if (action == null) {
                // Default action to list all offers
                List<Offer> offers = offerDao.getAllOffers();
                request.setAttribute("offers", offers);
                RequestDispatcher dispatcher = request.getRequestDispatcher("offers.jsp");
                dispatcher.forward(request, response);
            } else if ("view".equals(action)) {
                // View a specific offer
                int offerId = Integer.parseInt(request.getParameter("offerId"));
                Offer offer = offerDao.getOfferById(offerId);
                request.setAttribute("offer", offer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("offer.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
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
                // Create a new offer
                String offerDescription = request.getParameter("offerDescription");
                Offer newOffer = new Offer(0, offerDescription);
                offerDao.addOffer(newOffer);

                response.sendRedirect("offerServlet?action=list");
            } else if ("update".equals(action)) {
                // Update an existing offer
                int offerId = Integer.parseInt(request.getParameter("offerId"));
                String offerDescription = request.getParameter("offerDescription");

                Offer updatedOffer = new Offer(offerId, offerDescription);
                offerDao.updateOffer(updatedOffer);

                response.sendRedirect("offerServlet?action=list");
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");
            }
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing POST request.", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int offerId = Integer.parseInt(request.getParameter("offerId"));
            offerDao.deleteOffer(offerId);

            response.sendRedirect("offerServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing DELETE request.", e);
        }
    }
}
