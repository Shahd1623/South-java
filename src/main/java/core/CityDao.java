package core;

import java.util.List;

public interface CityDao {
    // Retrieve all cities, potentially throwing an exception
    List<City> getAllCities() throws Exception;
}
