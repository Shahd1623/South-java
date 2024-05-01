<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Category List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Add CSS for styling -->
</head>
<body>
    <h1>Category List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of categories and display each in a table row -->
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                    <td>
                        <!-- Links for viewing, updating, and deleting categories -->
                        <a href="categoryServlet?action=get&categoryId=${category.categoryId}">View</a> |
                        <a href="updateCategory.jsp?categoryId=${category.categoryId}">Update</a> |
                        <a href="categoryServlet?action=delete&categoryId=${category.categoryId}" onclick="return confirm('Are you sure you want to delete this category?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
