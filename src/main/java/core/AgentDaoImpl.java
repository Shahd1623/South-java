package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class AgentDaoImpl implements AgentDao {
    private DatabaseConnection dbConnection;

    public AgentDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Agent> getAllAgents() throws Exception {
        List<Agent> agents = new ArrayList<>();
        String query = "SELECT * FROM Agents";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                agents.add(new Agent(
                    rs.getInt("agentId"),
                    rs.getString("agentName"),
                    rs.getString("contactInformation"),
                    rs.getString("email")
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all agents from the database", e);
        }

        return agents;
    }
}
