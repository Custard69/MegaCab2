/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller;

import com.customer.dao.BookingDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BookingCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int customerId = (int) session.getAttribute("customerId");  // assuming customer ID is stored in session

        // Check if the customer has an active or pending trip
        boolean hasActiveOrPendingBooking = BookingDAO.hasActiveBooking(customerId);

        if (hasActiveOrPendingBooking) {
            // Redirect to the requesting trip page if they already have an active or pending booking
            response.sendRedirect("requestingTrip.jsp");
        } else {
            // Otherwise, allow them to proceed to the customer booking page
            response.sendRedirect("customerBooking.jsp");
        }
    }
}