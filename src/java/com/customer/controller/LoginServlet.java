package com.customer.controller;

import com.customer.dao.CustomerDBUtil;
import com.customer.model.Customer;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("uid");
        String password = request.getParameter("pass");

        List<Customer> cusDetails = CustomerDBUtil.validate(userName, password);

        if (!cusDetails.isEmpty()) {
            Customer customer = cusDetails.get(0);  // Assuming only one user
            
            HttpSession session = request.getSession();
            session.setAttribute("cusDetails", cusDetails);
            session.setAttribute("customerId", customer.getCustomerId()); // ✅ Store Customer ID
            session.setAttribute("role", customer.getRole()); // ✅ Store Role
            
            // Debugging - Print session attributes
            System.out.println("Customer ID stored in session: " + customer.getCustomerId());
            System.out.println("Role stored in session: " + customer.getRole());

            // Redirect based on role
            if ("admin".equals(customer.getRole())) {
                response.sendRedirect("Admin/driverList.jsp");
            } else {
                response.sendRedirect("useraccount.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
