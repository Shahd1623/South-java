package core;

import core.PropertyImagesDao;
import core.PropertyImages;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyImagesDaoImpl implements PropertyImagesDao {
    private final Connection connection; // Database connection (for example, JDBC)

    public PropertyImagesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PropertyImages> getAllPropertyImages() throws SQLException {
        List<PropertyImages> images = new ArrayList<>();
        String sql = "SELECT * FROM property_images;"; // Modify to suit your database schema

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int imageId = rs.getInt("image_id");
                int listingId = rs.getInt("listing_id");
                String imageUrl = rs.getString("image_url");

                images.add(new PropertyImages(imageId, listingId, imageUrl));
            }
        }
        return images;
    }

    @Override
    public PropertyImages getPropertyImageById(int imageId) throws SQLException {
        String sql = "SELECT * FROM property_images WHERE image_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, imageId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int listingId = rs.getInt("listing_id");
                    String imageUrl = rs.getString("image_url");

                    return new PropertyImages(imageId, listingId, imageUrl);
                } else {
                    return null; // No matching image found
                }
            }
        }
    }

    @Override
    public void addPropertyImage(PropertyImages propertyImage) throws SQLException {
        String sql = "INSERT INTO property_images (listing_id, image_url) VALUES (?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, propertyImage.getListingId());
            ps.setString(2, propertyImage.getImageUrl());

            ps.executeUpdate();
        }
    }

    @Override
    public void updatePropertyImage(PropertyImages propertyImage) throws SQLException {
        String sql = "UPDATE property_images SET listing_id = ?, image_url = ? WHERE image_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, propertyImage.getListingId());
            ps.setString(2, propertyImage.getImageUrl());
            ps.setInt(3, propertyImage.getImageId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deletePropertyImage(int imageId) throws SQLException {
        String sql = "DELETE FROM property_images WHERE image_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, imageId);

            ps.executeUpdate();
        }
    }
}
