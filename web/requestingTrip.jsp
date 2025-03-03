<%@ page import="com.customer.model.Booking" %>
<%@ page import="com.customer.dao.BookingDAO" %>

<%
    int bookingId = -1;
    
    // Retrieve bookingId from request parameter or session
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
    <h2>Your Trip Status</h2>
    <p><strong>Status:</strong> <%= booking.getStatus() %></p>
    <p><strong>Pickup Location:</strong> <%= booking.getPickupLocation() %></p>
    <p><strong>Destination:</strong> <%= booking.getDestination() %></p>
    <p><strong>Car Type:</strong> <%= booking.getCarType() %></p>
    <p><strong>Distance:</strong> <%= booking.getDistance() %> km</p>
    <p><strong>Fare:</strong> Rs. <%= booking.getFare() %></p>

    <%
        // Check if a driver is assigned
        if ("Assigned".equals(booking.getStatus()) || "Driver is on the way".equalsIgnoreCase(booking.getStatus())) {
            if (booking.getDriverName() != null && booking.getDriverPhone() != null) {
    %>
            <h3>Driver Information</h3>
            <p><strong>Driver Name:</strong> <%= booking.getDriverName() %></p>
            <p><strong>Driver Phone:</strong> <%= booking.getDriverPhone() %></p>
            <p><strong>Car Number:</strong> <%= booking.getCarNumber() != null ? booking.getCarNumber() : "Not Available" %></p>
            <button onclick="window.location.href='tel:<%= booking.getDriverPhone() %>'">Contact Driver</button>
    <%
            } else {
    %>
            <p>Driver details are not yet available. Please wait...</p>
    <%
            }
        }
    %>

<% } else { %>
    <p>Unable to retrieve booking details. Please try again.</p>
<% } %>
