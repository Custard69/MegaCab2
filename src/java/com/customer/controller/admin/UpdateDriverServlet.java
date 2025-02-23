/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.admin;

import com.customer.dao.driver.DriverDAO;
import com.customer.model.driver.Driver;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int driverId = Integer.parseInt(request.getParameter("driverId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String carType = request.getParameter("carType");
        String licenseNumber = request.getParameter("licenseNumber");
        String status = request.getParameter("status");

        Driver driver = new Driver(driverId, name, email, phone, carType, licenseNumber, "", status);

        boolean isUpdated = DriverDAO.updateDriver(driver);

        if (isUpdated) {
            response.sendRedirect("Admin/driverList.jsp?success=Driver Updated");
        } else {
            response.sendRedirect("Admin/editDriver.jsp?id=" + driverId + "&error=Failed to Update Driver");
        }
    }
}
