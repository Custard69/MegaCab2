<%-- 
    Document   : useraccount.jsp
    Created on : Feb 15, 2025, 5:20:24 PM
    Author     : Shane Malcolm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <c:forEach var="cus" items="${cusDetails}">
            
            ${cus.id}
            ${cus.name}
            ${cus.email}
            ${cus.phone}
            ${cus.userName}
            ${cus.password}
            
        </c:forEach>
        
    </body>
</html>
