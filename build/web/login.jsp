<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Page</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light d-flex align-items-center justify-content-center vh-100">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h2 class="text-center">Login</h2>

                    <form action="log" method="post">
                        <div class="mb-3">
                            <label for="uid" class="form-label">User Name</label>
                            <input type="text" id="uid" name="uid" class="form-control" required> 
                        </div>
                        <div class="mb-3">
                            <label for="pass" class="form-label">Password</label>
                            <input type="password" id="pass" name="pass" class="form-control" required> 
                        </div>
                        <button type="submit" name="submit" class="btn btn-primary w-100">Login</button>
                    </form>
                    <hr>
                    <p class="text-center">Don't have an account? <a href="register.jsp">Register here</a></p>
                    <p class="text-center">Login as Driver <a href="Driver/driverLogin.jsp">Driver</a></p>
                </div>
            </div>
        </div>
    </div>
</div>

<%
    String loginMessage = (String) session.getAttribute("loginMessage");
    String loginStatus = (String) session.getAttribute("loginStatus");
    String redirectPage = (String) session.getAttribute("redirectPage");
    
    if (loginMessage != null) { 
%>
    <script>
        alert("<%= loginMessage %>");
        <% 
            session.removeAttribute("loginMessage"); 
            session.removeAttribute("loginStatus"); 
        %>
        
        <% if ("success".equals(loginStatus) && redirectPage != null) { %>
            window.location.href = "<%= redirectPage %>"; // Redirect after alert
        <% } %>
    </script>
<% 
        session.removeAttribute("redirectPage"); 
    } 
%>


<script>
    history.pushState(null, null, location.href);
    window.onpopstate = function () {
        history.go(1);
    };
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
