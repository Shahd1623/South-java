package core;

import java.util.List;

public interface PropertyImagesDao {
    List<PropertyImages> getAllPropertyImages() throws Exception; // Fetch all images
    PropertyImages getPropertyImageById(int imageId) throws Exception; // Fetch image by ID
    void addPropertyImage(PropertyImages propertyImage) throws Exception; // Add a new image
    void updatePropertyImage(PropertyImages propertyImage) throws Exception; // Update existing image
    void deletePropertyImage(int imageId) throws Exception; // Delete an image by ID
}
