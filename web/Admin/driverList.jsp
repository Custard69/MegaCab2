<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.dao.driver.DriverDAO, com.customer.model.driver.Driver, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver List</title>
</head>
<body>
    <h2>Registered Drivers</h2>
    
    <a href="registerDriver.jsp">Add New Driver</a>
    
    <% String successMessage = request.getParameter("success"); %>
    <% if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
    <% } %>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Car Type</th>
            <th>License Number</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        
        <% List<Driver> drivers = DriverDAO.getAllDrivers(); %>
        <% for (Driver driver : drivers) { %>
        <tr>
            <td><%= driver.getDriverId() %></td>
            <td><%= driver.getName() %></td>
            <td><%= driver.getEmail() %></td>
            <td><%= driver.getPhone() %></td>
            <td><%= driver.getCarType() %></td>
            <td><%= driver.getLicenseNumber() %></td>
            <td><%= driver.getStatus() %></td>
            <td><a href="editDriver.jsp?id=<%= driver.getDriverId() %>">Edit</a></td>

            
                <td><a href="../DeleteDriverServlet?id=<%= driver.getDriverId() %>" onclick="return confirm('Are you sure you want to delete this driver?');">Delete</a></td>

            
        </tr>
        <% } %>
    </table>
</body>
</html>
