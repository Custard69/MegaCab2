<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Mega City Cab</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            display: flex;
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
    </style>
</head>
<body>
    
    <!-- Sidebar Navigation -->
    <div class="sidebar">
        <h4 class="text-center">Mega City Cab</h4>
        <a href="AdminDashboardServlet">Dashboard</a>
        <a href="Admin/driverList.jsp">Manage Drivers</a>
        <a href="Admin/tripRequests.jsp">Manage Trips</a>
        <a href="#">Manage Customers</a>
        <a href="#">Admin Management</a>
    </div>
    
    <div class="content"><!-- Main Content -->
            <div class="container">
                 <nav class="navbar navbar-expand-lg navbar-light bg-light">
                    <div class="container-fluid">
                        <span class="navbar-brand">Admin Panel</span>
                        <button class="btn btn-danger">Logout</button>
                    </div>
                </nav>
            <h2>Dashboard Overview</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="card text-white bg-primary mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Total Drivers</h5>
                            <p class="card-text"><%= request.getAttribute("totalDrivers") %></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-warning mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Pending Trips</h5>
                            <p class="card-text"><%= request.getAttribute("pendingTrips") %></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card text-white bg-success mb-3">
                        <div class="card-body">
                            <h5 class="card-title">Completed Trips</h5>
                            <p class="card-text"><%= request.getAttribute("completedTrips") %></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
                
</body>
</html>
