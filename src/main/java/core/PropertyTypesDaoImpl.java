package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyTypesDaoImpl implements PropertyTypesDao {
    private final Connection connection; // Database connection (like JDBC)

    public PropertyTypesDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<PropertyTypes> getAllPropertyTypes() throws SQLException {
        List<PropertyTypes> types = new ArrayList<>();
        String sql = "SELECT * FROM property_types;"; // Adjust based on your schema

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int typeId = rs.getInt("type_id");
                String typeName = rs.getString("type_name");

                types.add(new PropertyTypes(typeId, typeName));
            }
        }

        return types;
    }

    @Override
    public PropertyTypes getPropertyTypeById(int typeId) throws SQLException {
        String sql = "SELECT * FROM property_types WHERE type_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, typeId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String typeName = rs.getString("type_name");

                    return new PropertyTypes(typeId, typeName);
                } else {
                    return null; // No matching type found
                }
            }
        }
    }

    @Override
    public void addPropertyType(PropertyTypes propertyType) throws SQLException {
        String sql = "INSERT INTO property_types (type_name) VALUES (?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, propertyType.getTypeName());

            ps.executeUpdate();
        }
    }

    @Override
    public void updatePropertyType(PropertyTypes propertyType) throws SQLException {
        String sql = "UPDATE property_types SET type_name = ? WHERE type_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, propertyType.getTypeName());
            ps.setInt(2, propertyType.getTypeId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deletePropertyType(int typeId) throws SQLException {
        String sql = "DELETE FROM property_types WHERE type_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, typeId);

            ps.executeUpdate();
        }
    }
}
