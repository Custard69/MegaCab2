<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession userSession = request.getSession(false);
    String customerName = (userSession != null && userSession.getAttribute("customerName") != null)
            ? (String) userSession.getAttribute("customerName") : "Guest";
%>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .header {
            background: #007BFF;
            color: white;
            padding: 15px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .nav-links {
            display: flex;
            gap: 15px;
        }
        .nav-links a {
            color: white;
            text-decoration: none;
            font-size: 16px;
        }
        .logout-btn {
            background: red;
            color: white;
            padding: 8px 15px;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="header">
        <div>
            <h2>Mega City Cab</h2>
        </div>
        <div class="nav-links">
            <a href="useraccount.jsp">Home</a>
            <a href="customerBooking.jsp">Book a Ride</a>
            <a href="viewBookings.jsp">My Bookings</a>
            <a href="help.jsp">Help</a>
        </div>
        <div>
            <span>Welcome, <%= customerName %></span>
            <form action="LogoutServlet" method="post" style="display:inline;">
                <button type="submit" class="logout-btn">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>
