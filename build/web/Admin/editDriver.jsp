<%-- 
    Document   : editDriver
    Created on : Feb 23, 2025, 2:10:36 PM
    Author     : xevon
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.driver.DriverDAO, com.customer.model.driver.Driver" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Driver</title>
</head>
<body>
    <h2>Edit Driver</h2>

    <%
        int driverId = Integer.parseInt(request.getParameter("id"));
        Driver driver = DriverDAO.getDriverById(driverId);
    %>

    <form action="../UpdateDriverServlet" method="post">
        <input type="hidden" name="driverId" value="<%= driver.getDriverId() %>">

        <label>Name:</label>
        <input type="text" name="name" value="<%= driver.getName() %>" required><br>

        <label>Email:</label>
        <input type="email" name="email" value="<%= driver.getEmail() %>" required><br>

        <label>Phone:</label>
        <input type="text" name="phone" value="<%= driver.getPhone() %>" required><br>

        <label>Car Type:</label>
        <select name="carType">
            <option value="Sedan" <%= driver.getCarType().equals("Sedan") ? "selected" : "" %>>Sedan</option>
            <option value="SUV" <%= driver.getCarType().equals("SUV") ? "selected" : "" %>>SUV</option>
            <option value="Mini" <%= driver.getCarType().equals("Mini") ? "selected" : "" %>>Mini</option>
        </select><br>

        <label>License Number:</label>
        <input type="text" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required><br>

        <label>Status:</label>
        <select name="status">
            <option value="Available" <%= driver.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
            <option value="On Duty" <%= driver.getStatus().equals("On Duty") ? "selected" : "" %>>On Duty</option>
        </select><br>

        <button type="submit">Update Driver</button>
    </form>

    <a href="driverList.jsp">Back to Driver List</a>
</body>
</html>
