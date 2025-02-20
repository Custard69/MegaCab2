package com.customer;

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
            
            //Check the role
            if(customer.getRole().equals("admin")){
                response.sendRedirect("admindashboard.jsp");
            }else{
                response.sendRedirect("useraccount.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
