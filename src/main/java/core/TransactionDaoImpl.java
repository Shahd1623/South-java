package core;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao {
    private final Connection connection; // Database connection (JDBC)

    public TransactionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions;"; // Adjust based on your schema

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int listingId = rs.getInt("listing_id");
                int buyerId = rs.getInt("buyer_id");
                int sellerId = rs.getInt("seller_id");
                java.sql.Date transactionDate = rs.getDate("transaction_date");
                BigDecimal transactionAmount = rs.getBigDecimal("transaction_amount");

                transactions.add(new Transaction(transactionId, listingId, buyerId, sellerId, transactionDate, transactionAmount));
            }
        }

        return transactions;
    }

    @Override
    public Transaction getTransactionById(int transactionId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transactionId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int listingId = rs.getInt("listing_id");
                    int buyerId = rs.getInt("buyer_id");
                    int sellerId = rs.getInt("seller_id");
                    Date transactionDate = rs.getDate("transaction_date");
                    BigDecimal transactionAmount = rs.getBigDecimal("transaction_amount");

                    return new Transaction(transactionId, listingId, buyerId, sellerId, transactionDate, transactionAmount);
                } else {
                    return null; // No matching transaction found
                }
            }
        }
    }

    @Override
    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (listing_id, buyer_id, seller_id, transaction_date, transaction_amount) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transaction.getListingId());
            ps.setInt(2, transaction.getBuyerId());
            ps.setInt(3, transaction.getSellerId());
            ps.setDate(4, transaction.getTransactionDate());
            ps.setBigDecimal(5, transaction.getTransactionAmount());

            ps.executeUpdate();
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) throws SQLException {
        String sql = "UPDATE transactions SET listing_id = ?, buyer_id = ?, seller_id = ?, transaction_date = ?, transaction_amount = ? WHERE transaction_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transaction.getListingId());
            ps.setInt(2, transaction.getBuyerId());
            ps.setInt(3, transaction.getSellerId());
            ps.setDate(4, transaction.getTransactionDate());
            ps.setBigDecimal(5, transaction.getTransactionAmount());
            ps.setInt(6, transaction.getTransactionId());

            ps.executeUpdate();
        }
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?;";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, transactionId);

            ps.executeUpdate();
        }
    }
}
