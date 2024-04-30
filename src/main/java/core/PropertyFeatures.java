package core;

public class PropertyFeatures {
    private int featureId; // Primary key
    private int listingId; // Foreign key to Listing, nullable
    private String featureName; // Can be nullable
    private String featureValue; // Can be nullable

    // Default constructor
    public PropertyFeatures() {}

    // Parameterized constructor for easy initialization
    public PropertyFeatures(int featureId, int listingId, String featureName, String featureValue) {
        this.featureId = featureId;
        this.listingId = listingId;
        this.featureName = featureName;
        this.featureValue = featureValue;
    }

    // Getters and setters for encapsulation
    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public Integer getListingId() {
        return listingId;
    }

    public void setListingId(Integer listingId) {
        this.listingId = listingId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}
