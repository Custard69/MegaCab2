/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.driver;

import com.customer.dao.BookingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xevon
 */


public class CompleteTripServlet extends HttpServlet {
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    int bookingId = Integer.parseInt(request.getParameter("bookingId"));

    if (BookingDAO.completeTrip(bookingId)) {
        request.getSession().setAttribute("successMessage", "Trip completed successfully!");
        response.sendRedirect("Driver/bookingHistory.jsp"); // Redirect to history
    } else {
        request.setAttribute("errorMessage", "Failed to complete the trip.");
        doGet(request, response);
    }
}

}

