<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Summary</title>
</head>
<body>
    <h2>Booking Summary</h2>

    <%
        double baseFare = (double) session.getAttribute("baseFare");
        String carType = (String) session.getAttribute("carType");
        double totalFare = baseFare; // Default for Mini

        if ("Sedan".equalsIgnoreCase(carType)) {
            totalFare = baseFare * 1.10; // 10% more
        } else if ("SUV".equalsIgnoreCase(carType)) {
            totalFare = baseFare * 1.15; // 15% more
        }

        session.setAttribute("totalFare", totalFare);
    %>

    <p>Pickup Location: <%= session.getAttribute("pickupLocation") %></p>
    <p>Destination: <%= session.getAttribute("destination") %></p>
    <p>Car Type: <%= carType %></p>
    <p>Distance Package: <%= session.getAttribute("distance") %> km</p>
    <p>Base Fare: Rs <%= baseFare %></p>
    <h3>Total Fare: Rs <%= totalFare %></h3>

    <form action="ConfirmBookingServlet" method="post">
        <input type="hidden" name="pickupLocation" value="<%= session.getAttribute("pickupLocation") %>">
        <input type="hidden" name="destination" value="<%= session.getAttribute("destination") %>">
        <input type="hidden" name="carType" value="<%= carType %>">
        <input type="hidden" name="distance" value="<%= session.getAttribute("distance") %>">
        <input type="hidden" name="totalFare" value="<%= totalFare %>">
        <button type="submit">Confirm Booking</button>
    </form>

    <!-- Display error message if booking fails -->
    <c:if test="${not empty param.error}">
        <p style="color: red;">${param.error}</p>
    </c:if>
</body>
</html>
