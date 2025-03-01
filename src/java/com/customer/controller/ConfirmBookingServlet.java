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


public class ConfirmBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        int customerId = (int) session.getAttribute("customerId");  // Ensure 'customerId' is correctly set in session
        String pickupLocation = request.getParameter("pickupLocation");
        String destination = request.getParameter("destination");
        String carType = request.getParameter("carType");
        int distance = Integer.parseInt(request.getParameter("distance"));
        double fare = Double.parseDouble(request.getParameter("totalFare"));

        Booking booking = new Booking(customerId, pickupLocation, destination, carType, distance, fare, "Pending");

        boolean bookingSuccess = BookingDAO.saveBooking(booking);  // Save the booking and get the bookingId

        if (bookingSuccess) {
            int bookingId = booking.getBookingId(); // Retrieve booking ID
            session.setAttribute("bookingId", bookingId); // Store in session (optional)

            // Redirect with bookingId as a URL parameter
            response.sendRedirect("requestingTrip.jsp?bookingId=" + bookingId);
        } else {
            response.sendRedirect("bookingSummary.jsp?error=Failed%20to%20confirm%20booking");
        }

    }
}
