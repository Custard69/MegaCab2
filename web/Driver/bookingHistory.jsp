<%-- 
    Document   : bookingHistory
    Created on : Mar 4, 2025, 2:44:18 AM
    Author     : xevon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    String successMessage = (String) session.getAttribute("successMessage");
    String errorMessage = (String) session.getAttribute("errorMessage");
    session.removeAttribute("successMessage");
    session.removeAttribute("errorMessage");
%>

<% if (successMessage != null) { %>
    <script type="text/javascript">
        alert("<%= successMessage %>");
    </script>
<% } %>

<% if (errorMessage != null) { %>
    <script type="text/javascript">
        alert("<%= errorMessage %>");
    </script>
<% } %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
