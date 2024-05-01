<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Agent List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Add CSS for styling -->
</head>
<body>
    <h1>Agent List</h1>
    
    <table border="1">
        <thead>
            <tr>
                <th>Agent ID</th>
                <th>Name</th>
                <th>Contact Information</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of agents and display each in a table row -->
            <c:forEach var="agent" items="${agents}">
                <tr>
                    <td>${agent.id}</td>
                    <td>${agent.name}</td>
                    <td>${agent.contactInformation}</td>
                    <td>${agent.email}</td>
                    <td>
                        <!-- Links for viewing, updating, and deleting agents -->
                        <a href="agentServlet?action=get&agentId=${agent.id}">View</a> |
                        <a href="updateAgent.jsp?agentId=${agent.id}">Update</a> |
                        <a href="agentServlet?action=delete&agentId=${agent.id}" onclick="return confirm('Are you sure you want to delete this agent?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
