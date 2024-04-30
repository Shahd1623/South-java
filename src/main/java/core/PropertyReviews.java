package core;

//For Date handling
import java.sql.Date;

public class PropertyReviews {
    private int reviewId; // Primary key
    private int listingId; // Foreign key, nullable
    private int userId; // Foreign key, nullable
    private Float rating; // Nullable (corresponds to FLOAT)
    private String reviewText; // Nullable (corresponds to VARCHAR(45))
    private Date reviewDate; // Nullable (DATE type)

    // Default constructor
    public PropertyReviews() {}

    // Parameterized constructor for initialization
    public PropertyReviews(int reviewId, int listingId, int userId, Float rating, 
                           String reviewText, Date reviewDate) {
        this.reviewId = reviewId;
        this.listingId = listingId;
        this.userId = userId;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
    }

    // Getters and setters for encapsulation
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}

