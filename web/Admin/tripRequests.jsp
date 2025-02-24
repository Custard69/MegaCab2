<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.BookingDAO, com.customer.dao.driver.DriverDAO, com.customer.model.Booking, com.customer.model.driver.Driver" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pending Trip Requests</title>
</head>
<body>
    <h2>Pending Trip Requests</h2>

    <table border="1">
        <tr>
            <th>Booking ID</th>
            <th>Pickup</th>
            <th>Destination</th>
            <th>Car Type</th>
            <th>Fare</th>
            <th>Assign Driver</th>
        </tr>

        <%
            List<Booking> trips = BookingDAO.getPendingTrips();
            for (Booking trip : trips) {
                // Fetch only drivers with the requested car type
                List<Driver> drivers = DriverDAO.getAvailableDrivers(trip.getCarType());
        %>
        <tr>
            <td><%= trip.getBookingId() %></td>
            <td><%= trip.getPickupLocation() %></td>
            <td><%= trip.getDestination() %></td>
            <td><%= trip.getCarType() %></td>
            <td>Rs <%= trip.getFare() %></td>
            <td>
                <form action="../AssignDriverServlet" method="post">
                    <input type="hidden" name="bookingId" value="<%= trip.getBookingId() %>">
                    <select name="driverId">
                        <% if (drivers.isEmpty()) { %>
                            <option value="">No available drivers</option>
                        <% } else { 
                            for (Driver driver : drivers) { %>
                                <option value="<%= driver.getDriverId() %>">
                                    <%= driver.getName() %> - <%= driver.getCarType() %> (Available)
                                </option>
                        <%  } 
                        } %>
                    </select>
                    <button type="submit" <% if (drivers.isEmpty()) { %>disabled<% } %>>Assign</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
