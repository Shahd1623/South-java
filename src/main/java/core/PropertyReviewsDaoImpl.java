package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyReviewsDaoImpl implements PropertyReviewsDao {
    private final Connection connection; // Database connection (JDBC)

    public PropertyReviewsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PropertyReviews> getAllPropertyReviews() throws SQLException {
        List<PropertyReviews> reviews = new ArrayList<>();
        String sql = "SELECT * FROM property_reviews;"; // Adjust based on your schema

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int reviewId = rs.getInt("review_id");
                int listingId = rs.getInt("listing_id");
                int userId = rs.getInt("user_id");
                Float rating = rs.getFloat("rating");
                String reviewText = rs.getString("review_text");
                java.sql.Date reviewDate = rs.getDate("review_date");

                reviews.add(new PropertyReviews(reviewId, listingId, userId, rating, reviewText, reviewDate));
            }
        }

        return reviews;
    }

    @Override
    public PropertyReviews getPropertyReviewById(int reviewId) throws SQLException {
        String sql = "SELECT * FROM property_reviews WHERE review_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reviewId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int listingId = rs.getInt("listing_id");
                    int userId = rs.getInt("user_id");
                    Float rating = rs.getFloat("rating");
                    String reviewText = rs.getString("review_text");
                    java.sql.Date reviewDate = rs.getDate("review_date");

                    return new PropertyReviews(reviewId, listingId, userId, rating, reviewText, reviewDate);
                } else {
                    return null; // No matching review found
                }
            }
        }
    }

    @Override
    public void addPropertyReview(PropertyReviews propertyReview) throws SQLException {
        String sql = "INSERT INTO property_reviews (listing_id, user_id, rating, review_text, review_date) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, propertyReview.getListingId());
            ps.setInt(2, propertyReview.getUserId());
            ps.setFloat(3, propertyReview.getRating());
            ps.setString(4, propertyReview.getReviewText());
            ps.setDate(5, propertyReview.getReviewDate());

            ps.executeUpdate();
        }
    }

    @Override
    public void updatePropertyReview(PropertyReviews propertyReview) throws SQLException {
        String sql = "UPDATE property_reviews SET listing_id = ?, user_id = ?, rating = ?, review_text = ?, review_date = ? WHERE review_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, propertyReview.getListingId());
            ps.setInt(2, propertyReview.getUserId());
            ps.setFloat(3, propertyReview.getRating());
            ps.setString(4, propertyReview.getReviewText());
            ps.setDate(5, propertyReview.getReviewDate());
            ps.setInt(6, propertyReview.getReviewId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deletePropertyReview(int reviewId) throws SQLException {
        String sql = "DELETE FROM property_reviews WHERE review_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, reviewId);

            ps.executeUpdate();
        }
    }
}
