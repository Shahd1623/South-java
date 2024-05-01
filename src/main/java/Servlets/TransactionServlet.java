package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.math.BigDecimal;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import core.Transaction;
import core.TransactionDao;
import core.TransactionDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/ransactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDao transactionDao;

    public TransactionServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/sothdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        transactionDao = new TransactionDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<Transaction> transactions = transactionDao.getAllTransactions();
                request.setAttribute("transactionList", transactions);
                RequestDispatcher dispatcher = request.getRequestDispatcher("transactionList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int transactionId = Integer.parseInt(request.getParameter("transactionId"));
                Transaction transaction = transactionDao.getTransactionById(transactionId);
                request.setAttribute("transaction", transaction);
                RequestDispatcher dispatcher = request.getRequestDispatcher("transaction.jsp");
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
                int buyerId = Integer.parseInt(request.getParameter("buyerId"));
                int sellerId = Integer.parseInt(request.getParameter("sellerId"));
                Date transactionDate = Date.valueOf(request.getParameter("transactionDate"));
                BigDecimal transactionAmount = new BigDecimal(request.getParameter("transactionAmount"));

                Transaction newTransaction = new Transaction(0, listingId, buyerId, sellerId, transactionDate, transactionAmount);
                transactionDao.addTransaction(newTransaction);

                response.sendRedirect("transactionServlet?action=list");
            } else if ("update".equals(action)) {
                int transactionId = Integer.parseInt(request.getParameter("transactionId"));
                int listingId = Integer.parseInt(request.getParameter("listingId"));
                int buyerId = Integer.parseInt(request.getParameter("buyerId"));
                int sellerId = Integer.parseInt(request.getParameter("sellerId"));
                Date transactionDate = Date.valueOf(request.getParameter("transactionDate"));
                BigDecimal transactionAmount = new BigDecimal(request.getParameter("transactionAmount"));

                Transaction updatedTransaction = new Transaction(transactionId, listingId, buyerId, sellerId, transactionDate, transactionAmount);
                transactionDao.updateTransaction(updatedTransaction);

                response.sendRedirect("transactionServlet?action=list");
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
            int transactionId = Integer.parseInt(request.getParameter("transactionId"));
            transactionDao.deleteTransaction(transactionId);
            response.sendRedirect("transactionServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
