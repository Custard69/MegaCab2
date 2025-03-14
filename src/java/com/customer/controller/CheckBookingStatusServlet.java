 package com.customer.controller;

import com.customer.dao.BookingDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckBookingStatusServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object bookingIdObj = session.getAttribute("bookingId");

        if (bookingIdObj == null) {
            response.getWriter().write("Error: Booking ID not found in session.");
            return;
        }

        int bookingId = (int) bookingIdObj;
        System.out.println("Booking ID in session: " + bookingId); // Debugging

        String status = BookingDAO.getBookingStatus(bookingId);
        System.out.println("Booking status from DB: " + status); // Debugging

        response.getWriter().write(status);
    }

}
           