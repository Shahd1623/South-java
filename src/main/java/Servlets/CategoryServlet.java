package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import core.Category;
import core.CategoryDao;
import core.CategoryDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/categoryServlet")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CategoryDao categoryDao;

    public CategoryServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "username", "password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        categoryDao = new CategoryDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<Category> categories = categoryDao.getAllCategories();
                request.setAttribute("categories", categories);
                RequestDispatcher dispatcher = request.getRequestDispatcher("categoryList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                Category category = categoryDao.getCategoryById(categoryId);
                request.setAttribute("category", category);
                RequestDispatcher dispatcher = request.getRequestDispatcher("category.jsp");
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
                String categoryName = request.getParameter("categoryName");

                Category newCategory = new Category(0, categoryName);
                categoryDao.addCategory(newCategory);

                response.sendRedirect("categoryServlet?action=list");
            } else if ("update".equals(action)) {
                int categoryId = Integer.parseInt(request.getParameter("categoryId"));
                String categoryName = request.getParameter("categoryName");

                Category updatedCategory = new Category(categoryId, categoryName);
                categoryDao.updateCategory(updatedCategory);

                response.sendRedirect("categoryServlet?action=list");
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
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            categoryDao.deleteCategory(categoryId);
            response.sendRedirect("categoryServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
