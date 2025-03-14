<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script>
        let status = "<%= request.getParameter("status") %>";
        if (status === "success") {
            alert("Registration successful! Please login.");
            window.location.href = "login.jsp";
        } else if (status === "failed") {
            alert("Registration failed! Please try again.");
            window.location.href = "register.jsp";
        } else if (status === "error") {
            alert("Something went wrong! Please try again later.");
            window.location.href = "register.jsp";
        }
    </script>
</head>
<body>
</body>
</html>
