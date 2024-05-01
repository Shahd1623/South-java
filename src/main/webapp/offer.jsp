<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Offers List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- If you have a CSS file -->
</head>
<body>
    <h1>Offers</h1>

    <a href="offerForm.jsp">Add New Offer</a> <!-- Link to form for adding a new offer -->

    <table border="1">
        <thead>
            <tr>
                <th>Offer ID</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of offers passed to the JSP -->
            <c:forEach var="offer" items="${offers}">
                <tr>
                    <td>${offer.offerId}</td>
                    <td>${offer.offerDescription}</td>
                    <td>
                        <!-- Link to view, edit, or delete each offer -->
                        <a href="offerServlet?action=view&offerId=${offer.offerId}">View</a> |
                        <a href="offerServlet?action=edit&offerId=${offer.offerId}">Edit</a> |
                        <a href="offerServlet?action=delete&offerId=${offer.offerId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
