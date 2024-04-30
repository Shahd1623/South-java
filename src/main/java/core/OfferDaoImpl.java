package core;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class OfferDaoImpl {
    private DatabaseConnection dbConnection;

    public OfferDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Retrieve all offers
    public List<Offer> getAllOffers() throws Exception {
        List<Offer> offers = new ArrayList<>();
        String query = "SELECT * FROM Offers"; // Adjust the table name as needed

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                offers.add(new Offer(
                    rs.getInt("offerId"),
                    rs.getString("offerDescription")
                ));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all offers from the database", e); // Proper error handling
        }

        return offers;
    }

    // Retrieve an offer by ID
    public Offer getOfferById(int offerId) throws Exception {
        String query = "SELECT * FROM Offers WHERE offerId = ?";
        Offer offer = null;

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, offerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    offer = new Offer(
                        rs.getInt("offerId"),
                        rs.getString("offerDescription")
                    );
                }
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving offer by ID from the database", e); // Exception handling
        }

        return offer;
    }

    // Create a new offer
    public void addOffer(Offer offer) throws Exception {
        String query = "INSERT INTO Offers (offerId, offerDescription) VALUES (?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, offer.getOfferId());
            stmt.setString(2, offer.getOfferDescription());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error inserting offer into the database", e);
        }
    }

    // Update an existing offer
    public void updateOffer(Offer offer) throws Exception {
        String query = "UPDATE Offers SET offerDescription = ? WHERE offerId = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, offer.getOfferDescription());
            stmt.setInt(2, offer.getOfferId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error updating offer in the database", e);
        }
    }

    // Delete an offer by ID
    public void deleteOffer(int offerId) throws Exception {
        String query = "DELETE FROM Offers WHERE offerId = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, offerId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DataAccessException("Error deleting offer from the database", e);
        }
    }
}
