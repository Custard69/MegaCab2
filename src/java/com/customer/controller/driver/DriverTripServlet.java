/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.driver;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xevon
 */

public class DriverTripServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("driverId");

        if (driverId != null) {
            Booking trip = BookingDAO.getCurrentTrip(driverId);
            request.setAttribute("trip", trip);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Driver/waitingForTrips.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("../login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        if (BookingDAO.completeTrip(bookingId)) {
            response.sendRedirect("tripHistory.jsp");
        } else {
            response.sendRedirect("waitingForTrips.jsp?error=Failed");
        }
    }
}
