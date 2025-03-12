<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book a Ride</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
             padding-top: 80px; 
            margin: 0;
            font-family: 'Poppins', sans-serif;
            
            background-color: black;
            position: relative;
            overflow-x: hidden;
        }

        /* Background Overlay with Blur & Darken Effect */
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
            filter: blur(8px); /* Adjust blur intensity */
            opacity: 0.4; /* Adjust darkness */
            z-index: -1; /* Push background behind content */
        }
        .container {
            margin-top: 40px;
        }
        .form-card {
            background-color: white;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .form-card h2 {
            margin-bottom: 20px;
            font-size: 1.8rem;
        }
        .btn-custom {
            background-color: #1E3A8A;  /* Deep blue */
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 1.1rem;
            border-radius: 5px;
            text-decoration: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Add subtle shadow */
            transition: all 0.3s ease;  /* Smooth transition for hover effect */
        }
        .btn-custom:hover {
            background-color: #3B82F6;  /* Light blue */
            transform: scale(1.05);  /* Slightly enlarge the button */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);  /* Add stronger shadow on hover */
        }
    </style>
</head>
<body style="background: linear-gradient(90deg, #000000, #022917);">

    <!-- Include Dashboard -->
    <jsp:include page="customerDashboard.jsp" />

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="form-card">
                    <h2>Book a Ride</h2>
                    <form action="CustomerBookingServlet" method="post">
                        <div class="mb-3">
                            <label for="pickupLocation" class="form-label">Pickup Location</label>
                            <input type="text" class="form-control" name="pickupLocation" id="pickupLocation" required>
                        </div>

                        <div class="mb-3">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" name="destination" id="destination" required>
                        </div>

                        <div class="mb-3">
                            <label for="carType" class="form-label">Car Type</label>
                            <select class="form-select" name="carType" id="carType" required>
                                <option value="Sedan">Sedan</option>
                                <option value="SUV">SUV</option>
                                <option value="Mini">Mini</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="distancePackage" class="form-label">Select Distance Package</label>
                            <select class="form-select" name="distancePackage" id="distancePackage" required>
                                <option value="10">1-10km - Rs 1200</option>
                                <option value="15">1-15km - Rs 2000</option>
                                <option value="20">1-20km - Rs 3000</option>
                                <option value="40">1-40km - Rs 5000</option>
                            </select>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-custom">View Bill Summary</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
