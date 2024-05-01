<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to CSS for styling -->
</head>
<body>
    <h1>User List</h1>

    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Registration Date</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of users and display each in a table row -->
            <c:forEach var="user" items="${userList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.registrationDate}</td>
                    <td>
                        <!-- Action links for viewing, updating, and deleting -->
                        <a href="userServlet?action=get&userId=${user.userId}">View</a> |
                        <a href="editUser.jsp?userId=${user.userId}">Edit</a> |
                        <a href="userServlet?action=delete&userId=${user.userId}" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Link to add a new user -->
    <p><a href="createUser.jsp">Add New User</a></p>
</body>
</html>
