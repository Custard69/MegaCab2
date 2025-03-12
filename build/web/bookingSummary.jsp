<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Summary</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
         body {
             padding-top: 80px; 
            margin: 0;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(90deg, #000000, #022917);
            
            position: relative;
            overflow-x: hidden;
        }

        body::before {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: black;
            background: url('css/images/background_1.jpeg') no-repeat center center fixed;
            background-size: cover;
            filter: blur(8px); 
            opacity: 0.4; 
            z-index: -1; 
        }

        .container {
            margin-top: 40px;
        }

        .card {
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .card h3 {
            color: #007BFF;
        }

        .btn-highlight {
            background-color: #007BFF;
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 1.1rem;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 123, 255, 0.5);
            transition: all 0.3s ease;
        }

        .btn-highlight:hover {
            background-color: #0056b3;
            transform: scale(1.05);
            box-shadow: 0 6px 10px rgba(0, 123, 255, 0.7);
        }

        .btn-highlight:active {
            background-color: #003366;
            box-shadow: 0 4px 6px rgba(0, 123, 255, 0.9);
        }
    </style>
</head>
<body>

    <jsp:include page="customerDashboard.jsp" />

    <div class="container">
        <div class="card">
            <h2 class="mb-4">Booking Preview</h2>

            <%
                double baseFare = (double) session.getAttribute("baseFare");
                double discount = (double) session.getAttribute("discount");
                double totalFare = (double) session.getAttribute("totalFare");
                int distance = (int) session.getAttribute("distance");
                double discountPercentage = (double) session.getAttribute("discountPercentage");
            %>

            <p><strong>Pickup Location:</strong> <%= session.getAttribute("pickupLocation") %></p>
            <p><strong>Destination:</strong> <%= session.getAttribute("destination") %></p>
            <p><strong>Car Type:</strong> <%= session.getAttribute("carType") %></p>
            <p><strong>Distance Package:</strong> <%= distance %> km</p>
            <p><strong>Base Fare:</strong> Rs <%= baseFare %></p>
            <% if (discount > 0) { %>
                <p><strong>Discount Applied:</strong> <%= discountPercentage %>% (Rs <%= discount %>)</p>
            <% } %>
            <h3>Total Fare: Rs <%= totalFare %></h3>

            <form action="ConfirmBookingServlet" method="post">
                <input type="hidden" name="pickupLocation" value="<%= session.getAttribute("pickupLocation") %>">
                <input type="hidden" name="destination" value="<%= session.getAttribute("destination") %>">
                <input type="hidden" name="carType" value="<%= session.getAttribute("carType") %>">
                <input type="hidden" name="distance" value="<%= distance %>">
                <input type="hidden" name="totalFare" value="<%= totalFare %>">
                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-highlight">Confirm Booking</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
