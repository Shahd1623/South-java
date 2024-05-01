package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import core.User;
import core.UserDao;
import core.UserDaoImpl;
import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    public UserServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/your_database", "your_user", "your_password");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        userDao = new UserDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<User> users = userDao.getAllUsers();
                request.setAttribute("userList", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = userDao.getUserById(userId);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("user.jsp");
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
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Date registrationDate = Date.valueOf(request.getParameter("registrationDate"));

                User newUser = new User(0, username, email, password, registrationDate);
                userDao.addUser(newUser);

                response.sendRedirect("userServlet?action=list");
            } else if ("update".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Date registrationDate = Date.valueOf(request.getParameter("registrationDate"));

                User updatedUser = new User(userId, username, email, password, registrationDate);
                userDao.updateUser(updatedUser);

                response.sendRedirect("userServlet?action=list");
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
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
            response.sendRedirect("userServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
