package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyFeaturesDaoImpl implements PropertyFeaturesDao {
    private final Connection connection; // Database connection

    public PropertyFeaturesDaoImpl(Connection connection) {
        this.connection = connection; // Pass the connection to the DAO
    }

    @Override
    public List<PropertyFeatures> getAllPropertyFeatures() throws SQLException {
        List<PropertyFeatures> featuresList = new ArrayList<>();
        String sql = "SELECT * FROM property_features;"; // Adjust table name as needed

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int featureId = resultSet.getInt("feature_id");
                int listingId = resultSet.getInt("listing_id");
                String featureName = resultSet.getString("feature_name");
                String featureValue = resultSet.getString("feature_value");

                featuresList.add(new PropertyFeatures(featureId, listingId, featureName, featureValue));
            }
        }
        return featuresList;
    }

    @Override
    public PropertyFeatures getPropertyFeaturesById(int featureId) throws SQLException {
        String sql = "SELECT * FROM property_features WHERE feature_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, featureId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int listingId = resultSet.getInt("listing_id");
                    String featureName = resultSet.getString("feature_name");
                    String featureValue = resultSet.getString("feature_value");

                    return new PropertyFeatures(featureId, listingId, featureName, featureValue);
                } else {
                    return null; // No matching feature found
                }
            }
        }
    }

    @Override
    public void addPropertyFeatures(PropertyFeatures propertyFeatures) throws SQLException {
        String sql = "INSERT INTO property_features (listing_id, feature_name, feature_value) VALUES (?, ?, ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyFeatures.getListingId());
            preparedStatement.setString(2, propertyFeatures.getFeatureName());
            preparedStatement.setString(3, propertyFeatures.getFeatureValue());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updatePropertyFeatures(PropertyFeatures propertyFeatures) throws SQLException {
        String sql = "UPDATE property_features SET listing_id = ?, feature_name = ?, feature_value = ? WHERE feature_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, propertyFeatures.getListingId());
            preparedStatement.setString(2, propertyFeatures.getFeatureName());
            preparedStatement.setString(3, propertyFeatures.getFeatureValue());
            preparedStatement.setInt(4, propertyFeatures.getFeatureId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePropertyFeatures(int featureId) throws SQLException {
        String sql = "DELETE FROM property_features WHERE feature_id = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, featureId);

            preparedStatement.executeUpdate();
        }
    }
}