package com.customer.controller.admin;

import com.customer.service.DriverServiceFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterDriverServlet extends HttpServlet {
    private final DriverServiceFacade driverFacade = new DriverServiceFacade();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterDriverServlet called");

        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String carType = request.getParameter("carType");
        String licenseNumber = request.getParameter("licenseNumber");
        String password = request.getParameter("password");

        // Register driver using Facade
        boolean isAdded = driverFacade.registerDriver(name, email, phone, carType, licenseNumber, password);

        if (isAdded) {
            response.sendRedirect("Admin/driverList.jsp?success=Driver Added");
        } else {
            response.sendRedirect("Admin/registerDriver.jsp?error=Failed to Add Driver");
        }
    }
}
