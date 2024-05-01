<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Property Types</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>Property Types List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Type ID</th>
                <th>Type Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of property types and display each in a table row -->
            <c:forEach var="propertyType" items="${propertyTypesList}">
                <tr>
                    <td>${propertyType.typeId}</td>
                    <td>${propertyType.typeName}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="propertyTypesServlet?action=get&typeId=${propertyType.typeId}">View</a> |
                        <a href="editPropertyType.jsp?typeId=${propertyType.typeId}">Edit</a> |
                        <a href="propertyTypesServlet?action=delete&typeId=${propertyType.typeId}" onclick="return confirm('Are you sure you want to delete this type?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Link to add a new property type -->
    <p><a href="createPropertyType.jsp">Add New Property Type</a></p>
</body>
</html>
