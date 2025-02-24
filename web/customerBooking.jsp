<%-- 
    Document   : customerBooking
    Created on : Feb 23, 2025, 5:03:15 PM
    Author     : xevon
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book a Ride</title>
</head>
<body>
    <h2>Book a Ride</h2>
    
    <form action="CustomerBookingServlet" method="post">
        <label>Pickup Location:</label>
        <input type="text" name="pickupLocation" required><br>

        <label>Destination:</label>
        <input type="text" name="destination" required><br>

        <label>Car Type:</label>
        <select name="carType">
            <option value="Sedan">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="Mini">Mini</option>
        </select><br>

        <label>Select Distance Package:</label>
        <select name="distancePackage">
            <option value="10">1-10km - Rs 1200</option>
            <option value="15">1-15km - Rs 2000</option>
            <option value="20">1-20km - Rs 3000</option>
            <option value="40">1-40km - Rs 5000</option>
        </select><br>

        <button type="submit">View Bill Summary</button>
    </form>
</body>
</html>
