<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>City Information</title>
    <link rel="stylesheet" type="text/css" href="style.css"> <!-- Link to a CSS file for styling -->
</head>
<body>
    <h1>City Information</h1>
    
    <!-- Form to collect city information -->
    <form action="cityServlet" method="post"> <!-- Assuming there's a cityServlet to handle the form -->
        <label for="city_id">City ID:</label>
        <input type="number" id="city_id" name="city_id" required>
        <br>
        <label for="city_name">City Name:</label>
        <input type="text" id="city_name" name="city_name" required>
        <br>
        <input type="submit" value="Submit">
    </form>

    <hr>

    <!-- Display city details if provided -->
    <c:if test="${not empty city}">
        <h2>City Details:</h2>
        <p>City ID: ${city.city_id}</p>
        <p>City Name: ${city.city_name}</p>
    </c:if>
    
    <!-- Link to return to the city list or other related pages -->
    <p><a href="cityList.jsp">Back to City List</a></p>
</body>
</html>
