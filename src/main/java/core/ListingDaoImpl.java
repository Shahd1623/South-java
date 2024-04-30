package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class ListingDaoImpl implements ListingDao {
    private DatabaseConnection dbConnection;

    public ListingDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Listing> getAllListings() throws Exception {
        List<Listing> listings = new ArrayList<>();
        String sql = "SELECT * FROM Listings"; // Adjust table name as needed

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                listings.add(new Listing.Builder(rs.getInt("listingId"))
                    .withCityId(rs.getInt("cityId"))
                    .withCategoryId(rs.getInt("categoryId"))
                    .withAgentId(rs.getInt("agentId"))
                    .withPrice(rs.getString("price"))
                    .withBedrooms(rs.getInt("bedrooms"))
                    .withBathrooms(rs.getInt("bathrooms"))
                    .withSquareFootage(rs.getDouble("squareFootage"))
                    .withOfferId(rs.getInt("offerId"))
                    .withTypeId(rs.getInt("typeId"))
                    .withDocumentId(rs.getInt("documentId"))
                    .build());
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving all listings from the database", e);
        }

        return listings;
    }
}
