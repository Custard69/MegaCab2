/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.admin;

import com.customer.dao.driver.DriverDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteDriverServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String driverId = request.getParameter("id");

        if (driverId != null) {
            boolean isDeleted = DriverDAO.deleteDriver(Integer.parseInt(driverId));

            if (isDeleted) {
                response.sendRedirect("Admin/driverList.jsp?success=Driver deleted successfully");
            } else {
                response.sendRedirect("Admin/driverList.jsp?error=Failed to delete driver");
            }
        }
    }
}