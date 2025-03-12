<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.customer.model.Customer"%>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("customerId") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mega City Cab - Dashboard</title>
    <!-- Bootstrap -->
<!--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">-->
    <link href="css/userAccount.css" rel="stylesheet"> <!-- External CSS -->
</head>
<body style="    background: linear-gradient(90deg, #000000, #022917);">

    <!-- Header -->
    <jsp:include page="customerDashboard.jsp" />
    <!-- Hero Section -->
    <div class="hero d-flex justify-content-between align-items-center">
        <!-- Hero Text Overlay (left side) -->
        <div class="hero-content text-left text-white">
            <h1>Fast, Reliable, <span style="color: yellow;">Safe</span></h1>
            <p>Book your ride in seconds and enjoy seamless travel.</p>
            <a href="BookingCheckServlet" class="book-btn">BOOK A CAB</a>
        </div>

        <!-- Discount Content Slides (right side) -->
       <div id="discountCarousel" class="carousel slide" data-bs-ride="carousel" data-bs-interval="4000" style="width: 40%;">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <div class="discount-slide">
                    <h3><span>5%</span> off for trips over 10km</h3>
                </div>
            </div>
            <div class="carousel-item">
                <div class="discount-slide">
                    <h3><span>10%</span> off for trips over 15km</h3>
                </div>
            </div>
            <div class="carousel-item">
                <div class="discount-slide">
                    <h3><span>15%</span> off for trips over 20km</h3>
                </div>
            </div>
        </div>

        <!-- Carousel Indicators (Tabs) -->
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#discountCarousel" data-bs-slide-to="0" class="active"></button>
            <button type="button" data-bs-target="#discountCarousel" data-bs-slide-to="1"></button>
            <button type="button" data-bs-target="#discountCarousel" data-bs-slide-to="2"></button>
        </div>
    </div>

    </div>

    <!-- Bootstrap JS -->
<!--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>-->
</body>
</html>
