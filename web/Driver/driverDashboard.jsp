<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.customer.model.driver.Driver" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("driver") == null) {
        response.sendRedirect("driverLogin.jsp");
        return;
    }
    Driver driver = (Driver) sessionObj.getAttribute("driver");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Driver Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= driver.getName() %></h2>
    <p>Email: <%= driver.getEmail() %></p>
    <p>Phone: <%= driver.getPhone() %></p>
    <p>Car Type: <%= driver.getCarType() %></p>
    <p>Status: <%= driver.getStatus() %></p>

    <a href="../DriverLogoutServlet">Logout</a>
</body>
</html>
