package core;

public class Listing {
    private int listingId;
    private Integer cityId; // Nullable fields should use the wrapper class
    private Integer categoryId;
    private Integer agentId;
    private String price;
    private Integer bedrooms;
    private Integer bathrooms;
    private Double squareFootage;
    private Integer offerId;
    private Integer typeId;
    private Integer documentId;

    // Constructor with required fields
    public Listing(int listingId) {
        this.listingId = listingId;
    }

    // Overloaded constructor with additional fields
    public Listing(int listingId, Integer cityId, Integer categoryId, Integer agentId, 
                   String price, Integer bedrooms, Integer bathrooms, Double squareFootage,
                   Integer offerId, Integer typeId, Integer documentId) {
        this.listingId = listingId;
        this.cityId = cityId;
        this.categoryId = categoryId;
        this.agentId = agentId;
        this.price = price;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.squareFootage = squareFootage;
        this.offerId = offerId;
        this.typeId = typeId;
        this.documentId = documentId;
    }

    // Getters and setters for all fields
    public int getListingId() {
        return listingId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Double getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(Double squareFootage) {
        this.squareFootage = squareFootage;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }
}
