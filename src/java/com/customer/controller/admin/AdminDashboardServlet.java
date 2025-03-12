/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.admin;


import com.customer.dao.AdminDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int totalDrivers = AdminDAO.getTotalDrivers(); // Get total drivers
        int pendingTrips = AdminDAO.getPendingTripCount(); // Get pending trips count
        int completedTrips = AdminDAO.getCompletedTripCount(); // Get completed trips count

        // Set attributes for JSP
        request.setAttribute("totalDrivers", totalDrivers);
        request.setAttribute("pendingTrips", pendingTrips);
        request.setAttribute("completedTrips", completedTrips);

        // Forward to adminDashboard.jsp
        request.getRequestDispatcher("Admin/adminDashboard.jsp").forward(request, response);
    }
}