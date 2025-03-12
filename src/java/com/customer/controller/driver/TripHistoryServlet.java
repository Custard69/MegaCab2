/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.driver;

import com.customer.dao.BookingDAO;
import com.customer.model.Booking;
import com.customer.model.driver.Driver;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xevon
 */


public class TripHistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    
    // Retrieve driver object instead of just driverId
    Driver driver = (Driver) session.getAttribute("driver");
    
    // Debugging: Check if driver object is present
    if (driver == null) {
        System.out.println("Driver not found in session. Redirecting to login.");
        response.sendRedirect("driverLogin.jsp");
        return;
    }

    int driverId = driver.getDriverId(); // Extract driverId from driver object

    // Debugging: Print driver ID
    System.out.println("Driver ID from session in TripHistoryServlet: " + driverId);

    List<Booking> completedTrips = BookingDAO.getCompletedTrips(driverId);

    // Debugging: Print number of trips retrieved
    System.out.println("Completed Trips fetched: " + completedTrips.size());

    request.setAttribute("completedTrips", completedTrips);
    request.getRequestDispatcher("Driver/tripHistory.jsp").forward(request, response);
}
}


