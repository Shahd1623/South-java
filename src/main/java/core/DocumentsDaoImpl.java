package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class DocumentsDaoImpl implements DocumentDao {
    private DatabaseConnection dbConnection;

    public DocumentsDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Documents> getAllDocuments() throws Exception {
        List<Documents> documents = new ArrayList<>();
        String query = "SELECT * FROM Documents"; // Adjust table name as needed

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                documents.add(new Documents(
                    rs.getInt("documents_id"),
                    rs.getString("documents_name"),
                    rs.getString("documents_description"),
                    rs.getString("documents_image")
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all documents from the database", e); // Proper error handling
        }

        return documents;
    }

    @Override
    public Documents getDocumentById(int documentId) throws Exception {
        String query = "SELECT * FROM Documents WHERE documents_id = ?";
        Documents document = null;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, documentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    document = new Documents(
                        rs.getInt("documents_id"),
                        rs.getString("documents_name"),
                        rs.getString("documents_description"),
                        rs.getString("documents_image")
                    );
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving document by ID from the database", e);
        }

        return document;
    }

    @Override
    public void addDocument(Documents document) throws Exception {
        String query = "INSERT INTO Documents (documents_id, documents_name, documents_description, documents_image) VALUES (?, ?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, document.getDocuments_id());
            stmt.setString(2, document.getDocuments_name());
            stmt.setString(3, document.getDocuments_description());
            stmt.setString(4, document.getDocuments_image());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error inserting document into the database", e);
        }
    }

    @Override
    public void updateDocument(Documents document) throws Exception {
        String query = "UPDATE Documents SET documents_name = ?, documents_description = ?, documents_image = ? WHERE documents_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, document.getDocuments_name());
            stmt.setString(2, document.getDocuments_description());
            stmt.setString(3, document.getDocuments_image());
            stmt.setInt(4, document.getDocuments_id());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error updating document in the database", e);
        }
    }

    @Override
    public void deleteDocument(int documentId) throws Exception {
        String query = "DELETE FROM Documents WHERE documents_id = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, documentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error deleting document from the database", e);
        }
    }
}
