<%@ page import="java.util.List, com.customer.model.Booking" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Booking History</title>
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">
    
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/header.css"> <!-- This contains the theme -->
<!--    <link rel="stylesheet" href="css/custom-style.css">  New file for styling -->

    <jsp:include page="customerDashboard.jsp" />

    <style>
        /* Ensure content starts below the fixed header */
         body {
             padding-top: 80px; 
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(90deg, #000000, #022917);
            
            
            position: relative;
            overflow-x: hidden;
        }

        /* Background Overlay with Blur & Darken Effect */
        body::before {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: black;
            background: url('css/images/background_1.jpeg') no-repeat center center fixed;
            background-size: cover;
            filter: blur(8px); /* Adjust blur intensity */
            opacity: 0.4; /* Adjust darkness */
            z-index: -1; /* Push background behind content */
        }

        /* Center the heading and apply theme colors */
        h2 {
            text-align: center;
            font-weight: bold;
            margin-bottom: 20px;
            color: var(--primary-color, #333); /* Use theme color */
        }

        /* Make sure the container has spacing from header */
        .container {
            margin-top: 20px;
            background: var(--background-color, #f8f9fa); /* Match theme */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Table styling to match theme */
        .table {
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        .table th {
            background: var(--primary-color, #007bff);
            color: white;
        }

        /* Button styling to match theme */
        .btn-info {
            background-color: var(--button-color, #28a745);
            border-color: var(--button-border, #28a745);
            transition: 0.3s ease-in-out;
            color: white;
        }

        .btn-info:hover {
            background-color: var(--button-hover, #18692b);
            border-color: var(--button-border-hover, #004085);
        }

        /* Alert box styling */
        .alert {
            text-align: center;
            font-size: 16px;
            color: var(--text-color, #333);
        }
    </style>
</head>
<body>
    
    <div class="container">
        <h2>Your Booking History</h2>
        
        <%
            HttpSession sessionObj = request.getSession(false);
            if (sessionObj == null || sessionObj.getAttribute("customerId") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <%
            List<Booking> bookings = (List<Booking>) request.getAttribute("bookingHistory");
            if (bookings == null || bookings.isEmpty()) {
        %>
            <div class="alert alert-info">No booking history found.</div>
        <%
            } else {
        %>
        
        <!-- Bootstrap Table -->
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Pickup</th>
                    <th>Destination</th>
                    <th>Car Type</th>
                    <th>Fare</th>
                    <th>Status</th>
                    <th>Driver</th>
                    <th>Contact</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Booking booking : bookings) {
                %>
                    <tr>
                        <td><%= booking.getPickupLocation() %></td>
                        <td><%= booking.getDestination() %></td>
                        <td><%= booking.getCarType() %></td>
                        <td><%= booking.getFare() %></td>
                        <td><%= booking.getStatus() %></td>
                        <td><%= (booking.getDriverName() != null) ? booking.getDriverName() : "Not Assigned" %></td>
                        <td><%= (booking.getDriverPhone() != null) ? booking.getDriverPhone() : "N/A" %></td>
                        <td>
                            <form action="tripSummary.jsp" method="get">
                                <input type="hidden" name="bookingId" value="<%= booking.getBookingId() %>" />
                                <button type="submit" class="btn btn-info btn-sm">View Summary</button>
                            </form>
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>

</body>
</html>
