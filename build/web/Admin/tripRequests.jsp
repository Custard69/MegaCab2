<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.BookingDAO, com.customer.dao.driver.DriverDAO, com.customer.model.Booking, com.customer.model.driver.Driver" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Trip Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            height: 100vh;
            margin: 0;
        }
        .sidebar {
            width: 250px;
            height: 100vh;
            position: fixed;
            background-color: #343a40;
            color: white;
            padding-top: 20px;
        }
        .sidebar a {
            color: white;
            padding: 10px;
            display: block;
            text-decoration: none;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .content {
            margin-left: 250px;
            padding: 20px;
            width: 100%;
        }
        .navbar {
            margin-left: 250px;
        }
    </style>
</head>
<body>

    <!-- Sidebar Navigation -->
    <div class="sidebar">
        <h4 class="text-center">Mega City Cab</h4>
        <a href="../AdminDashboardServlet">Dashboard</a>
        <a href="driverList.jsp">Manage Drivers</a>
        <a href="tripRequests.jsp">Manage Trips</a>
        <a href="#">Manage Customers</a>
        <a href="#">Admin Management</a>
    </div>

    <!-- Main Content -->
    <div class="content">
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <span class="navbar-brand">Admin Panel</span>
                <a href="../DriverLogoutServlet" class="btn btn-danger">Logout</a>
            </div>
        </nav>

        <div class="container mt-4">
            <h2>Pending Trip Requests</h2>

            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Pickup</th>
                        <th>Destination</th>
                        <th>Car Type</th>
                        <th>Fare</th>
                        <th>Assign Driver</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Booking> trips = BookingDAO.getPendingTrips();
                        for (Booking trip : trips) {
                            // Fetch only drivers with the requested car type
                            List<Driver> drivers = DriverDAO.getAvailableDrivers(trip.getCarType());
                    %>
                    <tr>
                        <td><%= trip.getBookingId() %></td>
                        <td><%= trip.getPickupLocation() %></td>
                        <td><%= trip.getDestination() %></td>
                        <td><%= trip.getCarType() %></td>
                        <td>Rs <%= trip.getFare() %></td>
                        <td>
                            <form action="../AssignDriverServlet" method="post">
                                <input type="hidden" name="bookingId" value="<%= trip.getBookingId() %>">
                                <select name="driverId" class="form-select">
                                    <% if (drivers.isEmpty()) { %>
                                        <option value="">No available drivers</option>
                                    <% } else { 
                                        for (Driver driver : drivers) { %>
                                            <option value="<%= driver.getDriverId() %>">
                                                <%= driver.getName() %> - <%= driver.getCarType() %> (Available)
                                            </option>
                                    <%  } 
                                    } %>
                                </select>
                                <button type="submit" class="btn btn-primary mt-2" <% if (drivers.isEmpty()) { %>disabled<% } %>>Assign</button>
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
                
                
                <h2>Ongoing Trip Requests</h2>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Booking ID</th>
                            <th>Pickup</th>
                            <th>Destination</th>
                            <th>Car Type</th>
                            <th>Fare</th>
                            <th>Assigned Driver</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Booking> ongoingTrips = BookingDAO.getOngoingTrips();
                            for (Booking trip : ongoingTrips) {
                        %>
                        <tr>
                            <td><%= trip.getBookingId() %></td>
                            <td><%= trip.getPickupLocation() %></td>
                            <td><%= trip.getDestination() %></td>
                            <td><%= trip.getCarType() %></td>
                            <td>Rs <%= trip.getFare() %></td>
                            <td>
                                <!-- Fetch the driver's name using the new method -->
                                <%= DriverDAO.getDriverNameForBooking(trip.getBookingId()) %>
                            </td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <h2>Completed Trip Requests</h2>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Pickup</th>
                        <th>Destination</th>
                        <th>Car Type</th>
                        <th>Fare</th>
                        <th>Driver</th>
                        <th>Completion Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Booking> completedTrips = BookingDAO.getCompletedTrips();
                        for (Booking trip : completedTrips) {
                    %>
                    <tr>
                        <td><%= trip.getBookingId() %></td>
                        <td><%= trip.getPickupLocation() %></td>
                        <td><%= trip.getDestination() %></td>
                        <td><%= trip.getCarType() %></td>
                        <td>Rs <%= trip.getFare() %></td>
                        <td>
                            <!-- Fetch the driver's name using the new method -->
                            <%= DriverDAO.getDriverNameForBooking(trip.getBookingId()) %>
                        </td>
                        <%
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        %>
                        <td><%= trip.getBookingDate() != null ? dateFormat.format(trip.getBookingDate()) : "N/A" %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

                
                
                
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
