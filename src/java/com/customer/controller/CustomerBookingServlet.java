package com.customer.controller;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerId = (Integer) session.getAttribute("customerId"); // Retrieve customer ID from session
        
        // Proceed with booking if no active trip
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String carType = request.getParameter("carType");
        int distance = Integer.parseInt(request.getParameter("distancePackage"));

        double baseFare = getBaseFare(distance);
        double discount = applyDiscount(distance);  // Updated to calculate discount based on distance
        double totalFare = baseFare - discount;

        // Store booking details in session for summary page
        session.setAttribute("pickupLocation", pickupLocation);
        session.setAttribute("destination", destination);
        session.setAttribute("carType", carType);
        session.setAttribute("distance", distance);
        session.setAttribute("baseFare", baseFare);
        session.setAttribute("discount", discount);
        session.setAttribute("totalFare", totalFare);
        session.setAttribute("discountPercentage", getDiscountPercentage(distance));  // Store discount percentage

        response.sendRedirect("bookingSummary.jsp");
    }

    private double getBaseFare(int distance) {
        switch (distance) {
            case 10: return 1200;
            case 15: return 2000;
            case 20: return 3000;
            case 40: return 5000;
            default: return 0;
        }
    }

    private double applyDiscount(int distance) {
        if (distance > 20) {
            return 0.15 * getBaseFare(distance);  // 15% discount
        } else if (distance > 15) {
            return 0.10 * getBaseFare(distance);  // 10% discount
        } else if (distance > 10) {
            return 0.05 * getBaseFare(distance);  // 5% discount
        }
        return 0;
    }

    private double getDiscountPercentage(int distance) {
        if (distance > 20) {
            return 15;
        } else if (distance > 15) {
            return 10;
        } else if (distance > 10) {
            return 5;
        }
        return 0;
    }
}
