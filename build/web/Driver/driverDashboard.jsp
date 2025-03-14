<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.model.driver.Driver" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("driver") == null) {
        response.sendRedirect("driverLogin.jsp");
        return;
    }
    Driver driver = (Driver) sessionObj.getAttribute("driver");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Driver Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Driver Dashboard</a>
        <a href="../DriverLogoutServlet" class="btn btn-danger">Logout</a>
    </div>
</nav>

<div class="container mt-4">
    <h2>Welcome, <%= driver.getName() %></h2>
    
    <div class="card mt-3">
        <div class="card-body">
            <p><strong>Email:</strong> <%= driver.getEmail() %></p>
            <p><strong>Phone:</strong> <%= driver.getPhone() %></p>
            <p><strong>Car Type:</strong> <%= driver.getCarType() %></p>
            <p><strong>Status:</strong> <%= driver.getStatus() %></p> <!-- Displaying Driver Status -->
        </div>
    </div>

    <div class="mt-4">
        <a href="../TripHistoryServlet" class="btn btn-primary">Trip History</a>
        <a href="waitingForTrips.jsp" class="btn btn-success">Wait for Trips</a>
        
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
