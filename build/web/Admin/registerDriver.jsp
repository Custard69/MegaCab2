<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Driver</title>
</head>
<body>
    <h2>Register New Driver</h2>
    
    <form action="../RegisterDriverServlet" method="post">
        <label>Name:</label>
        <input type="text" name="name" required><br>

        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Phone:</label>
        <input type="text" name="phone" required><br>

        <label>Car Type:</label>
        <select name="carType">
            <option value="Sedan">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="Mini">Mini</option>
        </select><br>

        <label>License Number:</label>
        <input type="text" name="licenseNumber" required><br>
        
        <label>Password:</label>
        <input type="password" name="password" required><br>

        <button type="submit">Register Driver</button>
    </form>

    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>
</body>
</html>

