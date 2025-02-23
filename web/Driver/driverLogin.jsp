<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Login</title>
</head>
<body>
    <h2>Driver Login</h2>

    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;">Invalid email or password. Please try again.</p>
    <% } %>

    <form action="../DriverLoginServlet" method="post">
        <label>Email:</label>
        <input type="email" name="email" required><br>

        <label>Password:</label>
        <input type="password" name="password" required><br>

        <button type="submit">Login</button>
    </form>
</body>
</html>
