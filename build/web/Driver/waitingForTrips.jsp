<%@page import="com.customer.model.driver.Driver"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.BookingDAO, com.customer.model.Booking" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    
    if (session == null || session.getAttribute("driver") == null) {
        response.sendRedirect("driverLogin.jsp");
        return;
    }
    


    int driverId = ((Driver) session.getAttribute("driver")).getDriverId();
    Booking currentTrip = BookingDAO.getCurrentTrip(driverId);  // Fetch current trip assigned to driver
%>
<% 
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
%>
    <div class="alert alert-success"><%= successMessage %></div>
<% 
        session.removeAttribute("successMessage");
    }
%>

<% 
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
    <div class="alert alert-danger"><%= errorMessage %></div>
<% } %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Waiting for Trips</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Driver Dashboard</a>
        <a href="../DriverLogoutServlet" class="btn btn-danger">Logout</a>
    </div>
</nav>
    <p>Driver ID: <%= driverId %></p>


<div class="container mt-4">
    <h2>Waiting for Trip Assignments</h2>
    
    <%
        if (currentTrip != null) {
    %>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Assigned Trip</h5>
                <p><strong>Pickup Location:</strong> <%= currentTrip.getPickupLocation() %></p>
                <p><strong>Destination:</strong> <%= currentTrip.getDestination() %></p>
                <p><strong>Fare:</strong> Rs. <%= currentTrip.getFare() %></p>
                
                <form action="../CompleteTripServlet" method="post">
                    <input type="hidden" name="bookingId" value="<%= currentTrip.getBookingId() %>">
                    <button type="submit" class="btn btn-success">Complete Trip</button>
                </form>
            </div>
        </div>
    <%
        } else {
    %>
        <p>No trip assigned. Please wait for a trip.</p>
    <%
        }
    %>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
