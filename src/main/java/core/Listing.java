package core;

public class Listing {
    private int listingId; // Primary key
    private int cityId; // Nullable fields can use Integer (wrapper class)
    private int categoryId;
    private int agentId;
    private String price;
    private int bedrooms; // Nullable
    private int bathrooms;
    private Double squareFootage;
    private int offerId;
    private int typeId;
    private int documentId;

    // Private constructor, only accessible by the Builder
    private Listing(Builder builder) {
        this.listingId = builder.listingId; // Required
        this.cityId = builder.cityId;
        this.categoryId = builder.categoryId;
        this.agentId = builder.agentId;
        this.price = builder.price;
        this.bedrooms = builder.bedrooms;
        this.bathrooms = builder.bathrooms;
        this.squareFootage = builder.squareFootage;
        this.offerId = builder.offerId;
        this.typeId = builder.typeId;
        this.documentId = builder.documentId;
    }

    // Getters and setters for all fields
    public int getListingId() {
        return listingId;
    }

    public int getCityId() {
        return cityId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getAgentId() {
        return agentId;
    }

    public String getPrice() {
        return price;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public Double getSquareFootage() {
        return squareFootage;
    }

    public int getOfferId() {
        return offerId;
    }

    public int getTypeId() {
        return typeId;
    }

    public int getDocumentId() {
        return documentId;
    }

    // Nested Builder class
    public static class Builder {
        private final int listingId; // Required field
        private int cityId; // Optional fields
        private int categoryId;
        private int agentId;
        private String price;
        private int bedrooms;
        private int bathrooms;
        private Double squareFootage;
        private int offerId;
        private int typeId;
        private int documentId;

        // Builder constructor with required field
        public Builder(int listingId) {
            this.listingId = listingId;
        }

        // Chained methods for setting optional fields
        public Builder withCityId(int cityId) {
            this.cityId = cityId;
            return this;
        }

        public Builder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder withAgentId(int agentId) {
            this.agentId = agentId;
            return this;
        }

        public Builder withPrice(String price) {
            this.price = price;
            return this;
        }

        public Builder withBedrooms(int bedrooms) {
            this.bedrooms = bedrooms;
            return this;
        }

        public Builder withBathrooms(int bathrooms) {
            this.bathrooms = bathrooms;
            return this;
        }

        public Builder withSquareFootage(Double squareFootage) {
            this.squareFootage = squareFootage;
            return this;
        }

        public Builder withOfferId(int offerId) {
            this.offerId = offerId;
            return this;
        }

        public Builder withTypeId(int typeId) {
            this.typeId = typeId;
            return this;
        }

        public Builder withDocumentId(int documentId) {
            this.documentId = documentId;
            return this;
        }

        public Listing build() {
            return new Listing(this);
        }
    }
}
