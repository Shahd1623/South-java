package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConfig;
import Database.DatabaseConnection;
import Database.MySqlDatabaseConnection;
import core.Agent;
import core.AgentDao;
import core.AgentDaoImpl;
import jakarta.servlet.*;

@WebServlet("/agentServlet")
public class AgentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AgentDao agentDao;

    public AgentServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Initialize the DAO with a specific database configuration
        DatabaseConfig config = new DatabaseConfig("jdbc:mysql://localhost:3306/southdb", "root", "root");
        DatabaseConnection dbConnection = new MySqlDatabaseConnection(config);
        agentDao = new AgentDaoImpl(dbConnection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("list".equals(action)) {
                List<Agent> agents = agentDao.getAllAgents();
                request.setAttribute("agents", agents);
                RequestDispatcher dispatcher = request.getRequestDispatcher("agentList.jsp");
                dispatcher.forward(request, response);
            } else if ("get".equals(action)) {
                int agentId = Integer.parseInt(request.getParameter("agentId"));
                Agent agent = agentDao.getAgentById(agentId);
                request.setAttribute("agent", agent);
                RequestDispatcher dispatcher = request.getRequestDispatcher("agent.jsp");
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
                String agentName = request.getParameter("agentName");
                String contactInformation = request.getParameter("contactInformation");
                String email = request.getParameter("email");

                Agent newAgent = new Agent(0, agentName, contactInformation, email);
                agentDao.addAgent(newAgent);

                response.sendRedirect("agentServlet?action=list");
            } else if ("update".equals(action)) {
                int agentId = Integer.parseInt(request.getParameter("agentId"));
                String agentName = request.getParameter("agentName");
                String contactInformation = request.getParameter("contactInformation");
                String email = request.getParameter("email");

                Agent updatedAgent = new Agent(agentId, agentName, contactInformation, email);
                agentDao.updateAgent(updatedAgent);

                response.sendRedirect("agentServlet?action=list");
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
            int agentId = Integer.parseInt(request.getParameter("agentId"));
            agentDao.deleteAgent(agentId);
            response.sendRedirect("agentServlet?action=list");
        } catch (SQLException | NumberFormatException e) {
            throw new ServletException("Error processing request.", e);
        }
    }
}
