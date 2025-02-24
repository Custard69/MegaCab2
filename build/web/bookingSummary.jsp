<%-- 
    Document   : bookingSummary
    Created on : Feb 23, 2025, 5:09:58 PM
    Author     : xevon
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Summary</title>
</head>
<body>
    <h2>Booking Summary</h2>

    <p>Pickup Location: <%= session.getAttribute("pickupLocation") %></p>
    <p>Destination: <%= session.getAttribute("destination") %></p>
    <p>Car Type: <%= session.getAttribute("carType") %></p>
    <p>Distance Package: <%= session.getAttribute("distance") %> km</p>
    <p>Base Fare: Rs <%= session.getAttribute("baseFare") %></p>
    <p>Discount: Rs <%= session.getAttribute("discount") %></p>
    <h3>Total Fare: Rs <%= session.getAttribute("totalFare") %></h3>

    <form action="ConfirmBookingServlet" method="post">
        <input type="hidden" name="pickupLocation" value="<%= session.getAttribute("pickupLocation") %>">
        <input type="hidden" name="destination" value="<%= session.getAttribute("destination") %>">
        <input type="hidden" name="carType" value="<%= session.getAttribute("carType") %>">
        <input type="hidden" name="distance" value="<%= session.getAttribute("distance") %>">
        <input type="hidden" name="totalFare" value="<%= session.getAttribute("totalFare") %>">
        <button type="submit">Confirm Booking</button>
    </form>
        <!-- Display error message if booking fails -->
<c:if test="${not empty param.error}">
    <p style="color: red;">${param.error}</p>
</c:if>
</body>
</html>
