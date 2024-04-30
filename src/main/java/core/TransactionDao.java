package core;

import java.util.List;

public interface TransactionDao {
    List<Transaction> getAllTransactions() throws Exception; // Retrieve all transactions
    Transaction getTransactionById(int transactionId) throws Exception; // Retrieve by ID
    void addTransaction(Transaction transaction) throws Exception; // Add a new transaction
    void updateTransaction(Transaction transaction) throws Exception; // Update an existing transaction
    void deleteTransaction(int transactionId) throws Exception; // Delete a transaction by ID
}
