package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class CategoryDaoImpl {
    private DatabaseConnection dbConnection;

    public CategoryDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM Categories"; // Adjust the table name as needed

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(new Category(
                    rs.getInt("categoryId"),
                    rs.getString("categoryName")
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all categories from the database", e); // Exception handling
        }

        return categories;
    }
}
