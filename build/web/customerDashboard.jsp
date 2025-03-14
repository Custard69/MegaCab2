<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj != null && sessionObj.getAttribute("cusDetails") != null) {
        List<com.customer.model.Customer> cusDetails = (List<com.customer.model.Customer>) sessionObj.getAttribute("cusDetails");
        com.customer.model.Customer customer = cusDetails.get(0); // Assuming only one user

        int customerId = customer.getCustomerId(); // ✅ Correct way to get ID
//        out.println("Customer ID: " + customerId); // Debugging output
//    } else {
//        out.println("No customer in session!");
    }
%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
%>
<%
    
    if (sessionObj == null || sessionObj.getAttribute("customerId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - User Dashboard</title>
    


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
</head>
<body>
    <link rel="stylesheet" href="css/header.css">


    <header class="navbar">
        <div class="logo">MEGA CITY CAB</div>
        <nav>
            <ul>
                <li><a href="useraccount.jsp">Home</a></li>
                <li><a href="BookingCheckServlet">Book a Ride</a></li>
                <li><a href="BookingHistoryServlet">Booking History</a></li>
                
            </ul>
        </nav>
        
    


            <form action="CustomerLogoutServlet" method="get" style="display:inline;">
                <button type="submit" class="btn-signin">Logout</button>
            </form>
        
   </header>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
