package com.customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "customer";

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";
        String pass = "Smj139@1";
        
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection con = DriverManager.getConnection(url, user, pass);

            // Insert user details into the database
            String sql = "INSERT INTO customer (name, email, phone, username, password, role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, username);
            stmt.setString(5, password);
            stmt.setString(6, role);

            int rowsInserted = stmt.executeUpdate();
            stmt.close();
            con.close();

            if (rowsInserted > 0) {
                response.sendRedirect("login.jsp?message=Registration successful, please login");
            } else {
                response.sendRedirect("register.jsp?error=Registration failed, try again");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Something went wrong, try again");
        }
    }
}
