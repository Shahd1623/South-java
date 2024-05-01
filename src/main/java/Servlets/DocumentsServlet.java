package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import core.Documents;
import core.DocumentDao;
import core.DocumentsDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/documentsServlet")
public class DocumentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DocumentDao documentsDao;

    public DocumentsServlet() {
        super();
    }

    @Override
    public void init() {
        // Initialize the DAO with specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/southdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        documentsDao = new DocumentsDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<Documents> documents = documentsDao.getAllDocuments();
                request.setAttribute("documents", documents);
                RequestDispatcher dispatcher = request.getRequestDispatcher("documentsList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int documentId = Integer.parseInt(request.getParameter("documentId"));
                Documents document = documentsDao.getDocumentById(documentId);
                request.setAttribute("document", document);
                RequestDispatcher dispatcher = request.getRequestDispatcher("document.jsp");
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
                String docName = request.getParameter("docName");
                String docDescription = request.getParameter("docDescription");
                String docImage = request.getParameter("docImage");

                Documents newDocument = new Documents(0, docName, docDescription, docImage);
                documentsDao.addDocument(newDocument);

                response.sendRedirect("documentsServlet?action=list");
            } else if ("update".equals(action)) {
                int documentId = Integer.parseInt(request.getParameter("documentId"));
                String docName = request.getParameter("docName");
                String docDescription = request.getParameter("docDescription");
                String docImage = request.getParameter("docImage");

                Documents updatedDocument = new Documents(documentId, docName, docDescription, docImage);
                documentsDao.updateDocument(updatedDocument);

                response.sendRedirect("documentsServlet?action=list");
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
            int documentId = Integer.parseInt(request.getParameter("documentId"));
            documentsDao.deleteDocument(documentId);
            response.sendRedirect("documentsServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
