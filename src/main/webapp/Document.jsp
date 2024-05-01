<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Documents List</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Include if you have a CSS file -->
</head>
<body>
    <h1>Documents</h1>

    <a href="documentForm.jsp">Add New Document</a> <!-- Link to form for adding a new document -->

    <table border="1">
        <thead>
            <tr>
                <th>Document ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Image</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the list of documents passed to the JSP -->
            <c:forEach var="document" items="${documents}">
                <tr>
                    <td>${document.documents_id}</td>
                    <td>${document.documents_name}</td>
                    <td>${document.documents_description}</td>
                    <td><img src="${document.documents_image}" alt="Document Image" width="50" height="50"></td>
                    <td>
                        <a href="documentServlet?action=view&documentId=${document.documents_id}">View</a> |
                        <a href="documentServlet?action=edit&documentId=${document.documents_id}">Edit</a> |
                        <a href="documentServlet?action=delete&documentId=${document.documents_id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
