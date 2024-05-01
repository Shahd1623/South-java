<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Property Images</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>Property Images List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Image ID</th>
                <th>Listing ID</th>
                <th>Image URL</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of property images and display each in a table row -->
            <c:forEach var="propertyImage" items="${propertyImagesList}">
                <tr>
                    <td>${propertyImage.imageId}</td>
                    <td>${propertyImage.listingId}</td>
                    <td>${propertyImage.imageUrl}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="propertyImagesServlet?action=get&imageId=${propertyImage.imageId}">View</a> |
                        <a href="editPropertyImage.jsp?imageId=${propertyImage.imageId}">Edit</a> |
                        <a href="propertyImagesServlet?action=delete&imageId=${propertyImage.imageId}" onclick="return confirm('Are you sure you want to delete this image?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Link to create a new property image -->
    <p><a href="createPropertyImage.jsp">Add New Property Image</a></p>
</body>
</html>
