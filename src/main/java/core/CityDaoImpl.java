package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DataAccessException;
import Database.DatabaseConnection;

public class CityDaoImpl implements CityDao {
    private DatabaseConnection dbConnection;

    public CityDaoImpl(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<City> getAllCities() throws Exception {
        List<City> cities = new ArrayList<>();
        String sql = "SELECT * FROM Cities";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int city_id = rs.getInt("city_id");
                String city_name = rs.getString("city_name");
                cities.add(new City(city_id, city_name));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Error accessing the database while retrieving cities", e); // Wrap SQLException
        }

        return cities;
    }
}
