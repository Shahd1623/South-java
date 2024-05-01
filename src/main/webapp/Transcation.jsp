<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transaction List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>Transaction List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Listing ID</th>
                <th>Buyer ID</th>
                <th>Seller ID</th>
                <th>Transaction Date</th>
                <th>Transaction Amount</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of transactions and display each in a table row -->
            <c:forEach var="transaction" items="${transactionList}">
                <tr>
                    <td>${transaction.transactionId}</td>
                    <td>${transaction.listingId}</td>
                    <td>${transaction.buyerId}</td>
                    <td>${transaction.sellerId}</td>
                    <td>${transaction.transactionDate}</td>
                    <td>${transaction.transactionAmount}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="transactionServlet?action=get&transactionId=${transaction.transactionId}">View</a> |
                        <a href="editTransaction.jsp?transactionId=${transaction.transactionId}">Edit</a> |
                        <a href="transactionServlet?action=delete&transactionId=${transaction.transactionId}" onclick="return confirm('Are you sure you want to delete this transaction?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Link to create a new transaction -->
    <p><a href="createTransaction.jsp">Add New Transaction</a></p>
</body>
</html>
