/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CustomerBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String carType = request.getParameter("carType");
        int distance = Integer.parseInt(request.getParameter("distancePackage"));

        double baseFare = getBaseFare(distance);
        double discount = applyDiscount(baseFare);
        double totalFare = baseFare - discount;

        // Store booking details in session for summary page
        HttpSession session = request.getSession();
        session.setAttribute("pickupLocation", pickupLocation);
        session.setAttribute("destination", destination);
        session.setAttribute("carType", carType);
        session.setAttribute("distance", distance);
        session.setAttribute("baseFare", baseFare);
        session.setAttribute("discount", discount);
        session.setAttribute("totalFare", totalFare);

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

    private double applyDiscount(double baseFare) {
        return (baseFare > 10000) ? baseFare * 0.10 : 0;
    }
}
