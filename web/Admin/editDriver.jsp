<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.driver.DriverDAO, com.customer.model.driver.Driver" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Driver - Mega City Cab</title>
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
            <h2>Edit Driver</h2>

            <%
                int driverId = Integer.parseInt(request.getParameter("id"));
                Driver driver = DriverDAO.getDriverById(driverId);
            %>

            <form action="../UpdateDriverServlet" method="post">
                <input type="hidden" name="driverId" value="<%= driver.getDriverId() %>">

                <div class="mb-3">
                    <label class="form-label">Name:</label>
                    <input type="text" class="form-control" name="name" value="<%= driver.getName() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="email" class="form-control" name="email" value="<%= driver.getEmail() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone:</label>
                    <input type="text" class="form-control" name="phone" value="<%= driver.getPhone() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Car Type:</label>
                    <select class="form-select" name="carType">
                        <option value="Sedan" <%= driver.getCarType().equals("Sedan") ? "selected" : "" %>>Sedan</option>
                        <option value="SUV" <%= driver.getCarType().equals("SUV") ? "selected" : "" %>>SUV</option>
                        <option value="Mini" <%= driver.getCarType().equals("Mini") ? "selected" : "" %>>Mini</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">License Number:</label>
                    <input type="text" class="form-control" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Status:</label>
                    <select class="form-select" name="status">
                        <option value="Available" <%= driver.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
                        <option value="On Duty" <%= driver.getStatus().equals("On Duty") ? "selected" : "" %>>On Duty</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Update Driver</button>
            </form>

            <a href="driverList.jsp" class="btn btn-secondary mt-3">Back to Driver List</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
