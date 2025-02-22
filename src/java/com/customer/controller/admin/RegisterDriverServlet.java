/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.admin;

import com.customer.model.driver.Driver;
import com.customer.dao.driver.DriverDAO;  
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterDriverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("RegisterDriverServlet called");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String carType = request.getParameter("carType");
        String licenseNumber = request.getParameter("licenseNumber");
        String password = request.getParameter("password");

        Driver driver = new Driver(0, name, email, phone, carType, licenseNumber,password,  "Available");

        boolean isAdded = DriverDAO.addDriver(driver);

        if (isAdded) {
            response.sendRedirect("Admin/driverList.jsp?success=Driver Added");
        } else {
            response.sendRedirect("Admin/registerDriver.jsp?error=Failed to Add Driver");
        }
    }
}
