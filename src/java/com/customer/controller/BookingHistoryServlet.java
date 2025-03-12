package com.customer.controller;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BookingHistoryServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not authenticated
            return;
        }

        int customerId = (int) session.getAttribute("customerId"); // ✅ Get customerId from session
        List<Booking> bookingHistory = BookingDAO.getBookingsByCustomerId(customerId); // Fetch bookings

        request.setAttribute("bookingHistory", bookingHistory); // Send data to JSP
        request.getRequestDispatcher("customerBookingHistory.jsp").forward(request, response); // Forward to JSP
    }
}
