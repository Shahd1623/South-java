package core;

import java.util.List;

public interface PropertyTypesDao {
    List<PropertyTypes> getAllPropertyTypes() throws Exception; // Retrieve all property types
    PropertyTypes getPropertyTypeById(int typeId) throws Exception; // Retrieve by ID
    void addPropertyType(PropertyTypes propertyType) throws Exception; // Add a new type
    void updatePropertyType(PropertyTypes propertyType) throws Exception; // Update existing type
    void deletePropertyType(int typeId) throws Exception; // Delete by ID
}
