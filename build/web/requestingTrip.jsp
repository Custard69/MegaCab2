<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Requesting Trip...</title>
    <style>
        .loader {
            border: 8px solid #f3f3f3;
            border-top: 8px solid #3498db;
            border-radius: 50%;
            width: 50px;
            height: 50px;
            animation: spin 1s linear infinite;
            margin: 20px auto;
        }
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
    <script>
        function checkBookingStatus() {
            fetch('../CheckBookingStatusServlet')
                .then(response => response.text())
                .then(status => {
                    if (status === 'Confirmed') {
                        window.location.href = 'tripConfirmed.jsp';
                    } else {
                        setTimeout(checkBookingStatus, 5000);
                    }
                });
        }
        window.onload = checkBookingStatus;
    </script>
</head>
<body>
    <h2>Requesting a Trip...</h2>
    <div class="loader"></div>
    <p>Please wait while we assign a driver...</p>
</body>
</html>
