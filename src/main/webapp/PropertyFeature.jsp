<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Property Features</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>Property Features List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Feature ID</th>
                <th>Listing ID</th>
                <th>Feature Name</th>
                <th>Feature Value</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of property features and display each in a table row -->
            <c:forEach var="propertyFeature" items="${propertyFeaturesList}">
                <tr>
                    <td>${propertyFeature.featureId}</td>
                    <td>${propertyFeature.listingId}</td> <!-- Fixing the missing closing tag -->
                    <td>${propertyFeature.featureName}</td>
                    <td>${propertyFeature.featureValue}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="propertyFeatureServlet?action=get&featureId=${propertyFeature.featureId}">View</a> |
                        <a href="editPropertyFeature.jsp?featureId=${propertyFeature.featureId}">Edit</a> |
                        <a href="propertyFeatureServlet?action=delete&featureId=${propertyFeature.featureId}" onclick="return confirm('Are you sure you want to delete this feature?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Link to create a new property feature -->
    <p><a href="createPropertyFeature.jsp">Add New Property Feature</a></p>
</body>
</html>
