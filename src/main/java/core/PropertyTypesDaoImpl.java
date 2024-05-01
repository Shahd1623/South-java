package core;

import java.sql.*;
import java.util.*;

import Database.DatabaseConnection;

public class PropertyTypesDaoImpl implements PropertyTypesDao {
    private final DatabaseConnection dbConnection;

    public PropertyTypesDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public void addType(PropertyTypes type) throws SQLException {
        Connection conn = dbConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO property_types (type_name) VALUES (?)");
        pstmt.setString(1, type.getTypeName());
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    @Override
    public List<PropertyTypes> getAllTypes() throws SQLException {
        Connection conn = dbConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM property_types");

        List<PropertyTypes> types = new ArrayList<>();
        while (rs.next()) {
            int typeId = rs.getInt("type_id");
            String typeName = rs.getString("type_name");

            types.add(new PropertyTypes(typeId, typeName));
        }

        rs.close();
        stmt.close();
        conn.close();

        return types;
    }

    @Override
    public PropertyTypes getTypeById(int typeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM property_types WHERE type_id = ?");
        pstmt.setInt(1, typeId);
        ResultSet rs = pstmt.executeQuery();

        PropertyTypes type = null;
        if (rs.next()) {
            String typeName = rs.getString("type_name");

            type = new PropertyTypes(typeId, typeName);
        }

        rs.close();
        pstmt.close();
        conn.close();

        return type;
    }

    @Override
    public void updateType(PropertyTypes type) throws SQLException {
        Connection conn = dbConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("UPDATE property_types SET type_name = ? WHERE type_id = ?");
        pstmt.setString(1, type.getTypeName());
        pstmt.setInt(2, type.getTypeId());
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }

    @Override
    public void deleteType(int typeId) throws SQLException {
        Connection conn = dbConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM property_types WHERE type_id = ?");
        pstmt.setInt(1, typeId);
        pstmt.executeUpdate();

        pstmt.close();
        conn.close();
    }
}
