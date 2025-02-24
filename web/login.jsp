<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/styles.css">  <!-- Link the CSS -->
    </head>
    <body>
        <form action="log" method="post">
            <h2>Login</h2>
            <label for="uid">User Name</label>
            <input type="text" id="uid" name="uid" required> 
            
            <label for="pass">Password</label>
            <input type="password" id="pass" name="pass" required> 
            
            <input type="submit" name="submit" value="Login">
        </form>

        <p>Don't have an account? <a href="register.jsp">Register here</a></p>
    </body>
</html>
