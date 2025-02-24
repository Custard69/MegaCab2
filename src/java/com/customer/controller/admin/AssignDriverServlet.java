/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.admin;

import com.customer.dao.BookingDAO;
import com.customer.dao.driver.DriverDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AssignDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        int driverId = Integer.parseInt(request.getParameter("driverId"));

        boolean assigned = BookingDAO.assignDriverToTrip(bookingId, driverId);

        if (assigned) {
            DriverDAO.updateDriverStatus(driverId, "Busy"); // Mark driver as busy
            response.sendRedirect("Admin/tripRequests.jsp?success=Driver Assigned");
        } else {
            response.sendRedirect("Admin/tripRequests.jsp?error=Failed to assign driver");
        }
    }
}