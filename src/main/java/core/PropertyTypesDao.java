package core;

import java.sql.SQLException;
import java.util.List;

public interface PropertyTypesDao {
    void addType(PropertyTypes type) throws SQLException;
    List<PropertyTypes> getAllTypes() throws SQLException;
    PropertyTypes getTypeById(int typeId) throws SQLException;
    void updateType(PropertyTypes type) throws SQLException;
    void deleteType(int typeId) throws SQLException;
}
