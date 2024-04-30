package core;

import java.util.List;

public interface PropertyReviewsDao {
    List<PropertyReviews> getAllPropertyReviews() throws Exception; // Retrieve all reviews
    PropertyReviews getPropertyReviewById(int reviewId) throws Exception; // Retrieve by ID
    void addPropertyReview(PropertyReviews propertyReview) throws Exception; // Create a new review
    void updatePropertyReview(PropertyReviews propertyReview) throws Exception; // Update an existing review
    void deletePropertyReview(int reviewId) throws Exception; // Delete a review by ID
}
