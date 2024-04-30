package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.Offer;
import core.OfferDao;
import core.OfferDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

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
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "username", "password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        offerDao = new OfferDaoImpl(dbConnection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Offer> offers = offerDao.getAllOffers();
            request.setAttribute("offers", offers);
            RequestDispatcher dispatcher = request.getRequestDispatcher("offers.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database access error occurred", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests if needed
        doGet(request, response);
    }
}
