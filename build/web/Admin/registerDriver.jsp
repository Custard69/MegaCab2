<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Driver - Mega City Cab</title>
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
            <h2>Register New Driver</h2>

            <form action="../RegisterDriverServlet" method="post">
                <div class="mb-3">
                    <label class="form-label">Name:</label>
                    <input type="text" class="form-control" name="name" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email:</label>
                    <input type="email" class="form-control" name="email" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone:</label>
                    <input type="text" class="form-control" name="phone" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Car Type:</label>
                    <select class="form-select" name="carType">
                        <option value="Sedan">Sedan</option>
                        <option value="SUV">SUV</option>
                        <option value="Mini">Mini</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">License Number:</label>
                    <input type="text" class="form-control" name="licenseNumber" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Password:</label>
                    <input type="password" class="form-control" name="password" required>
                </div>

                <button type="submit" class="btn btn-primary">Register Driver</button>
            </form>

            <% if (request.getParameter("error") != null) { %>
                <div class="alert alert-danger mt-3">
                    <%= request.getParameter("error") %>
                </div>
            <% } %>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
