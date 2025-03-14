package com.customer.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String NIC = request.getParameter("NIC");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = "customer";

        String url = "jdbc:mysql://localhost:3306/mc";
        String user = "root";
        String pass = "admin";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            // Check if email or username already exists
            String checkQuery = "SELECT * FROM customer WHERE email = ? OR username = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setString(1, email);
            checkStmt.setString(2, username);
            ResultSet rs = checkStmt.executeQuery();

            HttpSession session = request.getSession();

            if (rs.next()) { // If record exists
                session.setAttribute("message", "Email or Username already exists!");
                session.setAttribute("status", "error");
                response.sendRedirect("register.jsp");
                return;
            }
            rs.close();
            checkStmt.close();

            // Insert user details into the database
            String sql = "INSERT INTO customer (name, email, NIC, phone, username, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, NIC);
            stmt.setString(4, phone);
            stmt.setString(5, username);
            stmt.setString(6, password);
            stmt.setString(7, role);

            int rowsInserted = stmt.executeUpdate();
            stmt.close();
            con.close();

            if (rowsInserted > 0) {
                session.setAttribute("message", "Registration successful! Please login.");
                session.setAttribute("status", "success");
            } else {
                session.setAttribute("message", "Registration failed! Please try again.");
                session.setAttribute("status", "error");
            }
            
            response.sendRedirect("register.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("message", "Something went wrong! Please try again later.");
            session.setAttribute("status", "error");
            response.sendRedirect("register.jsp");
        }
    }
}
