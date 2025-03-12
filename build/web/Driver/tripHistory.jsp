<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.customer.model.Booking" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    List<Booking> completedTrips = (List<Booking>) request.getAttribute("completedTrips");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trip History</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    

<div class="container mt-4">
    <h2 class="text-center mb-4">Trip History</h2>

    <% if (completedTrips != null && !completedTrips.isEmpty()) { %>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Time & Date</th>
                    <th>Pickup Location</th>
                    <th>Destination</th>
                    <th>Status</th>
                    <th>Fare (LKR)</th>
                </tr>
            </thead>
            <tbody>
                <% for (Booking trip : completedTrips) { %>
                    <tr>
                        <td><%= dateFormat.format(trip.getBookingDate()) %></td>
                        <td><%= trip.getPickupLocation() %></td>
                        <td><%= trip.getDestination() %></td>
                        <td><span class="badge bg-success"><%= trip.getStatus() %></span></td>
                        <td>Rs. <%= String.format("%.2f", trip.getFare()) %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <div class="alert alert-warning text-center">No completed trips found.</div>
    <% } %>

    <div class="text-center mt-3">
        <a href="Driver/driverDashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
