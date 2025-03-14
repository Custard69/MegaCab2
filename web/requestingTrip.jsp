<%@ page import="com.customer.model.Booking" %>
<%@ page import="com.customer.dao.BookingDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Trip Status</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    
    <jsp:include page="customerDashboard.jsp" />
    <style>
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
    </style>
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">Your Trip Status</h2>

    <%
        int bookingId = -1;

        if (request.getParameter("bookingId") != null) {
            bookingId = Integer.parseInt(request.getParameter("bookingId"));
        } else if (session.getAttribute("bookingId") != null) {
            bookingId = (int) session.getAttribute("bookingId");
        }

        Booking booking = null;

        if (bookingId != -1) {
            booking = BookingDAO.getBookingById(bookingId);  // Fetch booking details
        }
    %>

    <% if (booking != null) { %>
        <div class="card shadow-lg p-4">
            <h4 class="text-primary">Trip Details</h4>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><strong>Status:</strong> <span class="badge bg-info"><%= booking.getStatus() %></span></li>
                <li class="list-group-item"><strong>Pickup Location:</strong> <%= booking.getPickupLocation() %></li>
                <li class="list-group-item"><strong>Destination:</strong> <%= booking.getDestination() %></li>
                <li class="list-group-item"><strong>Car Type:</strong> <%= booking.getCarType() %></li>
                <li class="list-group-item"><strong>Distance:</strong> <%= booking.getDistance() %> km</li>
                <li class="list-group-item"><strong>Fare:</strong> <span class="fw-bold text-success">Rs. <%= booking.getFare() %></span></li>
            </ul>
            
                <% if (booking != null && "Completed".equalsIgnoreCase(booking.getStatus())) { %>
                    <div class="text-center mt-4">
                        <a href="customerBooking.jsp" class="btn btn-success btn-lg">
                            <i class="bi bi-car-front"></i> Book Another Ride
                        </a>
                    </div>
                <% } %>
 

            <%
                if ("Assigned".equals(booking.getStatus()) || "Driver is on the way".equalsIgnoreCase(booking.getStatus())) {
                    if (booking.getDriverName() != null && booking.getDriverPhone() != null) {
            %>
            <div class="mt-4">
                <h4 class="text-primary">Driver Information</h4>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><strong>Driver Name:</strong> <%= booking.getDriverName() %></li>
                    <li class="list-group-item"><strong>Driver Phone:</strong> <%= booking.getDriverPhone() %></li>
                    <li class="list-group-item"><strong>Car Number:</strong> <%= booking.getCarNumber() != null ? booking.getCarNumber() : "Not Available" %></li>
                </ul>
                <div class="mt-3 text-center">
                    <button class="btn btn-primary btn-lg" onclick="window.location.href='tel:<%= booking.getDriverPhone() %>'">
                        <i class="bi bi-telephone"></i> Contact Driver
                    </button>
                </div>
            </div>
            <%
                    } else {
            %>
            <div class="alert alert-warning mt-4 text-center">
                <strong>Driver details are not yet available. Please wait...</strong>
            </div>
            <%
                    }
                }
            %>
        </div>

    <% } else { %>
        <div class="alert alert-danger text-center mt-4">
            <p>Unable to retrieve booking details. Please try again.</p>
        </div>
    <% } %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
