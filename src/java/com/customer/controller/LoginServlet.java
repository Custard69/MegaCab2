   package com.customer.controller;

import com.customer.dao.CustomerDBUtil;
import com.customer.model.Customer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("uid");
        String password = request.getParameter("pass");

        HttpSession session = request.getSession();

        // Validate input
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
            session.setAttribute("loginMessage", "Username and Password cannot be empty!");
            session.setAttribute("loginStatus", "error");
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Customer> cusDetails = CustomerDBUtil.validate(userName, password);

            if (!cusDetails.isEmpty()) {
                Customer customer = cusDetails.get(0);

                session.setAttribute("cusDetails", cusDetails);
                session.setAttribute("customerId", customer.getCustomerId());
                session.setAttribute("role", customer.getRole());
                
                LOGGER.log(Level.INFO, "Customer ID stored in session: {0}", customer.getCustomerId());
                LOGGER.log(Level.INFO, "Role stored in session: {0}", customer.getRole());

                // Determine redirect page based on role
                String redirectPage = "useraccount.jsp"; // Default for customers
                if ("admin".equalsIgnoreCase(customer.getRole())) {
                    redirectPage = "AdminDashboardServlet"; // Redirect admin users
                }

                // Store success message and redirect target
                session.setAttribute("loginMessage", "Login successful!");
                session.setAttribute("loginStatus", "success");
                session.setAttribute("redirectPage", redirectPage);

                response.sendRedirect("login.jsp"); // Redirect to show alert first
            } else {
                session.setAttribute("loginMessage", "Invalid username or password!");
                session.setAttribute("loginStatus", "error");
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during login", e);
            session.setAttribute("loginMessage", "An unexpected error occurred. Please try again.");
            session.setAttribute("loginStatus", "error");
            response.sendRedirect("login.jsp");
        }
    }
}
