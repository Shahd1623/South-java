package core;

public class Offer{
    private int offerId; // Primary key
    private String offerDescription; // Can be nullable, corresponds to VARCHAR(45)

    // Default constructor
    public Offer() {}

    // Parameterized constructor
    public Offer(int offerId, String offerDescription) {
        this.offerId = offerId;
        this.offerDescription = offerDescription;
    }

    // Getters and setters for the fields
    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }
}

