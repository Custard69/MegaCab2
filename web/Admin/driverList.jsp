<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.driver.DriverDAO, com.customer.model.driver.Driver, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Driver List - Mega City Cab</title>
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
                <button class="btn btn-danger">Logout</button>
            </div>
        </nav>

        <div class="container mt-4">
            <h2>Registered Drivers</h2>
            
            <!-- Add New Driver Button -->
            <a href="registerDriver.jsp" class="btn btn-primary mb-3">Add New Driver</a>
            
            <% String successMessage = request.getParameter("success"); %>
            <% if (successMessage != null) { %>
                <p style="color: green;"><%= successMessage %></p>
            <% } %>

            <!-- Driver Table -->
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                        <th>Car Type</th>
                        <th>License Number</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <% List<Driver> drivers = DriverDAO.getAllDrivers(); %>
                    <% for (Driver driver : drivers) { %>
                    <tr>
                        <td><%= driver.getDriverId() %></td>
                        <td><%= driver.getName() %></td>
                        <td><%= driver.getEmail() %></td>
                        <td><%= driver.getPhone() %></td>
                        <td><%= driver.getCarType() %></td>
                        <td><%= driver.getLicenseNumber() %></td>
                        <td><%= driver.getStatus() %></td>
                        <td>
                            <a href="editDriver.jsp?id=<%= driver.getDriverId() %>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="../DeleteDriverServlet?id=<%= driver.getDriverId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this driver?');">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
