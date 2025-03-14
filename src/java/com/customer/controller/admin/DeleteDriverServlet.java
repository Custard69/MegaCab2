package com.customer.controller.admin;

import com.customer.service.DriverServiceFacade;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteDriverServlet extends HttpServlet {
    private final DriverServiceFacade driverFacade = new DriverServiceFacade();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int driverId = Integer.parseInt(request.getParameter("id"));

            boolean isDeleted = driverFacade.deleteDriver(driverId);

            if (isDeleted) {
                response.sendRedirect("Admin/driverList.jsp?success=Driver deleted successfully");
            } else {
                response.sendRedirect("Admin/driverList.jsp?error=Failed to delete driver");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("Admin/driverList.jsp?error=Invalid driver ID");
        }
    }
}
