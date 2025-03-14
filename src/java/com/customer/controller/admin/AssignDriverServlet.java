package com.customer.controller.admin;

import com.customer.service.BookingServiceFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssignDriverServlet extends HttpServlet {
    private final BookingServiceFacade bookingFacade = new BookingServiceFacade();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));
            int driverId = Integer.parseInt(request.getParameter("driverId"));

            boolean assigned = bookingFacade.assignDriver(bookingId, driverId);

            if (assigned) {
                response.sendRedirect("Admin/tripRequests.jsp?success=Driver Assigned");
            } else {
                response.sendRedirect("Admin/tripRequests.jsp?error=Failed to assign driver");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("Admin/tripRequests.jsp?error=Invalid booking or driver ID");
        }
    }
}
