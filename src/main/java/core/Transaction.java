package core;

//For BigDecimal handling
import java.math.BigDecimal;
//For Date handling
import java.sql.Date; 

public class Transaction {
    private int transactionId; // Primary key
    private int listingId; // Foreign key, nullable
    private int buyerId; // Foreign key, nullable
    private int sellerId; // Foreign key, nullable
    private Date transactionDate; // Nullable DATE
    private BigDecimal transactionAmount; // Nullable DECIMAL

    // Default constructor
    public Transaction() {}

    // Parameterized constructor for initializing the object with specific values
    public Transaction(int transactionId, int listingId, int buyerId, int sellerId, 
                       Date transactionDate,BigDecimal transactionAmount) {
        this.transactionId = transactionId;
        this.listingId = listingId;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    // Getters and setters for encapsulation
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}