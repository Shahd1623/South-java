package core;

public class PropertyImages {
    private int imageId; // Primary key
    private int listingId; // Foreign key, nullable
    private String imageUrl; // Nullable, corresponds to VARCHAR(45)

    // Default constructor
    public PropertyImages() {}

    // Parameterized constructor for initialization
    public PropertyImages(int imageId, int listingId, String imageUrl) {
        this.imageId = imageId;
        this.listingId = listingId;
        this.imageUrl = imageUrl;
    }

    // Getters and setters for encapsulation
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
