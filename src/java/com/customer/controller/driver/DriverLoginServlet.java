/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.customer.controller.driver;

import com.customer.dao.driver.DriverDAO;
import com.customer.model.driver.Driver;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DriverLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Driver driver = DriverDAO.authenticateDriver(email, password);

        if (driver != null) {
            HttpSession session = request.getSession();
            session.setAttribute("driver", driver);
            response.sendRedirect("Driver/driverLoginStatus.jsp?status=success");
        } else {
            response.sendRedirect("Driver/driverLoginStatus.jsp?status=failed");
        }
    }
}

