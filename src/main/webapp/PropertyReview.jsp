<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Property Reviews</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>Property Reviews List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Review ID</th>
                <th>Listing ID</th>
                <th>User ID</th>
                <th>Rating</th>
                <th>Review Text</th>
                <th>Review Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of property reviews and display each in a table row -->
            <c:forEach var="propertyReview" items="${propertyReviewsList}">
                <tr>
                    <td>${propertyReview.reviewId}</td>
                    <td>${propertyReview.listingId}</td>
                    <td>${propertyReview.userId}</td>
                    <td>${propertyReview.rating}</td>
                    <td>${propertyReview.reviewText}</td>
                    <td>${propertyReview.reviewDate}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="propertyReviewsServlet?action=get&reviewId=${propertyReview.reviewId}">View</a> |
                        <a href="editPropertyReview.jsp?reviewId=${propertyReview.reviewId}">Edit</a> |
                        <a href="propertyReviewsServlet?action=delete&reviewId=${propertyReview.reviewId}" onclick="return confirm('Are you sure you want to delete this review?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
