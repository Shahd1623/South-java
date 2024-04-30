package Servlets;

import core.UserDao;
import core.UserDaoImpl;
import core.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class UserServlet extends HttpServlet {
    private UserDao userDAO;

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a database connection
        Connection connection = null; // Replace with a valid database connection
        this.userDAO = new UserDaoImpl(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            
            if ("list".equals(action)) {
                // Fetch all users
                List<User> users = userDAO.getAllUsers();
                request.setAttribute("users", users);
                // Forward to a JSP page to display the list of users
                request.getRequestDispatcher("/users.jsp").forward(request, response);
            } else if ("get".equals(action)) {
                // Fetch a specific user by ID
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = userDAO.getUserById(userId);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/user.jsp").forward(request, response);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("create".equals(action)) {
                // Create a new user
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Date registrationDate = Date.valueOf(request.getParameter("registrationDate"));

                User newUser = new User(0, username, email, password, registrationDate);

                userDAO.addUser(newUser);

                // Redirect or send a success response
                response.sendRedirect("users?action=list");
            } else if ("update".equals(action)) {
                // Update an existing user
                int userId = Integer.parseInt(request.getParameter("userId"));
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                Date registrationDate = Date.valueOf(request.getParameter("registrationDate"));

                User updatedUser = new User(userId, username, email, password, registrationDate);

                userDAO.updateUser(updatedUser);

                response.sendRedirect("users?action=list");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));

            userDAO.deleteUser(userId);

            response.sendRedirect("users?action=list");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request.");
        }
    }
}
