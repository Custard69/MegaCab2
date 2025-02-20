<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h2>User Registration</h2>
        <form action="reg" method="post">
            Name: <input type="text" name="name" required><br>
            Email: <input type="email" name="email" required><br>
            Phone: <input type="text" name="phone" required><br>
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            <input type="submit" value="Register">
        </form>
        <br>
        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </body>
</html>
