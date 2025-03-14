<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="com.customer.model.Booking, com.customer.dao.BookingDAO" %>
<%@ page session="true" %>
<%
    // Get booking ID from request
    int bookingId = Integer.parseInt(request.getParameter("bookingId"));

    // Fetch booking details
    Booking booking = BookingDAO.getBookingById(bookingId);

    if (booking == null) {
        response.sendRedirect("customerBookingHistory.jsp"); // Redirect if no booking found
        return;
    }
%>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("customerId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Trip Summary</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" crossorigin="anonymous">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/header.css"> <!-- Assuming header.css contains theme -->
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

        /* Heading Styling */
        h2 {
            text-align: center;
            font-weight: bold;
            margin-bottom: 20px;
            color: var(--primary-color, #333); /* Theme color */
        }

        /* Container Styling */
        .container {
            margin-top: 20px;
            background: var(--background-color, #f8f9fa); /* Light background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        /* Table Styling */
        .table {
            background: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        .table th {
            background: var(--primary-color, #bbbbbb);
            color: black;
        }

        .table td {
            padding: 15px;
            text-align: center;
        }

        /* Button Styling */
        .btn-info {
            background-color: var(--button-color, #28a745);
            border-color: var(--button-border, #28a745);
            transition: 0.3s ease-in-out;
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
        <h2>Trip Summary</h2>
        
        <table class="table table-bordered table-striped">
            <tbody>
                <tr>
                    <th>Pickup Location</th>
                    <td><%= booking.getPickupLocation() %></td>
                </tr>
                <tr>
                    <th>Destination</th>
                    <td><%= booking.getDestination() %></td>
                </tr>
                <tr>
                    <th>Car Type</th>
                    <td><%= booking.getCarType() %></td>
                </tr>
                <tr>
                    <th>Distance</th>
                    <td><%= booking.getDistance() %> km</td>
                </tr>
                <tr>
                    <th>Fare</th>
                    <td>Rs. <%= booking.getFare() %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><%= booking.getStatus() %></td>
                </tr>
                <tr>
                    <th>Date & Time</th>
                        <%
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                %>
                <td><%= booking.getBookingDate() != null ? dateFormat.format(booking.getBookingDate()) : "N/A" %></td>

                </tr>
                <tr>
                    <th>Driver Name</th>
                    <td><%= booking.getDriverName() != null ? booking.getDriverName() : "Not Assigned" %></td>
                </tr>
                <tr>
                    <th>Driver Phone</th>
                    <td><%= booking.getDriverPhone() != null ? booking.getDriverPhone() : "Not Assigned" %></td>
                </tr>
                <tr>
                    <th>Car Number</th>
                    <td><%= booking.getCarNumber() != null ? booking.getCarNumber() : "Not Assigned" %></td>
                </tr>
            </tbody>
        </table>

        <form action="BookingHistoryServlet">
            <button type="submit" class="btn btn-info btn-sm">Back to History</button>
        </form>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" crossorigin="anonymous"></script>

</body>
</html>
