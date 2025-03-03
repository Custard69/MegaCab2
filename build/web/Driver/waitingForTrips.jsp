<%@ page import="com.customer.model.Booking" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    
    int bookingId = -1;
    if (request.getParameter("bookingId") != null) {
        bookingId = Integer.parseInt(request.getParameter("bookingId"));
    } else if (session.getAttribute("bookingId") != null) {
        bookingId = (int) session.getAttribute("bookingId");
    }
    out.println("Debug: bookingId = " + bookingId);
    Booking trip = (Booking) request.getAttribute("trip");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Waiting for Trips</title>
</head>
<body>
    <h2>Current Trip</h2>

    <% if (trip != null) { %>
        <p><strong>Pickup:</strong> <%= trip.getPickupLocation() %></p>
        <p><strong>Destination:</strong> <%= trip.getDestination() %></p>
        <p><strong>Customer ID:</strong> <%= trip.getCustomerId() %></p>
        <p><strong>Fare:</strong> $<%= trip.getFare() %></p>

        <form method="post" action="../DriverTripServlet">
            <input type="hidden" name="bookingId" value="<%= trip.getBookingId() %>">
            <button type="submit">Complete Trip</button>
        </form>
    <% } else { %>
        <p>No trips assigned yet. Please wait...</p>
    <% } %>

</body>
</html>
