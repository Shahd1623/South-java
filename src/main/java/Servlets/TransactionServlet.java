package Servlets;

import core.TransactionDao;
import core.TransactionDaoImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import core.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.List;

public class TransactionServlet extends HttpServlet {
    private TransactionDao transactionDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a database connection
        Connection connection = null; // You can replace with a valid connection instance
        this.transactionDAO = new TransactionDaoImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("list".equals(action)) {
                // Fetch all transactions and send to a JSP or JSON response
                List<Transaction> transactions = transactionDAO.getAllTransactions();
                request.setAttribute("transactions", transactions);
                // Forward to a JSP page to display transactions
                request.getRequestDispatcher("/transactions.jsp").forward(request, response);
            } else if ("get".equals(action)) {
                // Fetch a specific transaction by ID
                int transactionId = Integer.parseInt(request.getParameter("transactionId"));
                Transaction transaction = transactionDAO.getTransactionById(transactionId);
                // Forward to a JSP page or return as JSON
                request.setAttribute("transaction", transaction);
                request.getRequestDispatcher("/transaction.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Example: Create a new transaction
            int listingId = Integer.parseInt(request.getParameter("listingId"));
            int buyerId = Integer.parseInt(request.getParameter("buyerId"));
            int sellerId = Integer.parseInt(request.getParameter("sellerId"));
            Date transactionDate = Date.valueOf(request.getParameter("transactionDate"));
            BigDecimal transactionAmount = new BigDecimal(request.getParameter("transactionAmount"));

            Transaction newTransaction = new Transaction(0, listingId, buyerId, sellerId, transactionDate, transactionAmount);

            transactionDAO.addTransaction(newTransaction);

            // Redirect or send a success response
            response.sendRedirect("transactions?action=list");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }
}
