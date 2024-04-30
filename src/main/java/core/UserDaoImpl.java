package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final Connection connection; // Database connection (like JDBC)

    public UserDaoImpl(Connection connection) {
        this.connection = connection; // Pass in a database connection
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users;"; // Adjust to match your schema

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                java.sql.Date registrationDate = rs.getDate("registration_date");

                users.add(new User(userId, username, email, password, registrationDate));
            }
        }

        return users;
    }

    @Override
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    java.sql.Date registrationDate = rs.getDate("registration_date");

                    return new User(userId, username, email, password, registrationDate);
                } else {
                    return null; // No matching user found
                }
            }
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password, registration_date) VALUES (?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getRegistrationDate());

            ps.executeUpdate();
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, registration_date = ? WHERE user_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getRegistrationDate());
            ps.setInt(5, user.getUserId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);

            ps.executeUpdate();
        }
    }
}
