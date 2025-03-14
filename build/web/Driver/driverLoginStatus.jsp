<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <script>
        let status = "<%= request.getParameter("status") %>";
        if (status === "success") {
            alert("Login successful!");
            window.location.href = "driverDashboard.jsp";
        } else {
            alert("Login failed! Please check your email and password.");
            window.location.href = "driverLogin.jsp";
        }
    </script>
</head>
<body>
</body>
</html>
