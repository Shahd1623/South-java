package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import core.PropertyTypes;
import core.PropertyTypesDao;
import core.PropertyTypesDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/propertyTypesServlet")
public class PropertyTypesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PropertyTypesDao propertyTypesDao;

    public PropertyTypesServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/southdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        propertyTypesDao = new PropertyTypesDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<PropertyTypes> types = propertyTypesDao.getAllTypes();
                request.setAttribute("propertyTypesList", types);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyTypesList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int typeId = Integer.parseInt(request.getParameter("typeId"));
                PropertyTypes type = propertyTypesDao.getTypeById(typeId);
                request.setAttribute("propertyType", type);
                RequestDispatcher dispatcher = request.getRequestDispatcher("propertyType.jsp");
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
                String typeName = request.getParameter("typeName");

                PropertyTypes newType = new PropertyTypes(0, typeName);
                propertyTypesDao.addType(newType);

                response.sendRedirect("propertyTypesServlet?action=list");
            } else if ("update".equals(action)) {
                int typeId = Integer.parseInt(request.getParameter("typeId"));
                String typeName = request.getParameter("typeName");

                PropertyTypes updatedType = new PropertyTypes(typeId, typeName);
                propertyTypesDao.updateType(updatedType);

                response.sendRedirect("propertyTypesServlet?action=list");
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
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            propertyTypesDao.deleteType(typeId);
            response.sendRedirect("propertyTypesServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
