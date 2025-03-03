package com.customer.controller.driver;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet for the driver's "Waiting for Trips" page.
 */
public class WaitingForTripsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer driverId = (Integer) session.getAttribute("driverId");

        // If driver is not logged in, redirect to login page
        if (driverId == null) {
            response.sendRedirect("driverLogin.jsp");
            return;
        }

        // Retrieve the current assigned trip for the driver
        Booking assignedTrip = BookingDAO.getCurrentTrip(driverId);

        // If the driver is not assigned a trip, show an appropriate message
        if (assignedTrip == null) {
            request.setAttribute("message", "No trips assigned at the moment.");
        } else {
            request.setAttribute("assignedTrip", assignedTrip);
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("waitingForTrips.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));

        // Complete the trip
        if (BookingDAO.completeTrip(bookingId)) {
            response.sendRedirect("WaitingForTrips");
        } else {
            request.setAttribute("error", "Failed to complete the trip.");
            doGet(request, response);
        }
    }
}
