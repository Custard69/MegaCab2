/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.dao.driver;

import com.customer.model.driver.Driver;
import com.customer.dao.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DriverDAO {
    // Add New Driver
    public static boolean addDriver(Driver driver) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("INSERT INTO drivers (name, email, phone, carType, licenseNumber,password, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            
            pst.setString(1, driver.getName());
            pst.setString(2, driver.getEmail());
            pst.setString(3, driver.getPhone());
            pst.setString(4, driver.getCarType());
            pst.setString(5, driver.getLicenseNumber());
            pst.setString(6, driver.getPassword());
            pst.setString(7, driver.getStatus());

            success = pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    // Get All Drivers
    public static List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM drivers");
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("carType"),
                        rs.getString("licenseNumber"),
                        rs.getString("password"),
                        rs.getString("status")
                );
                drivers.add(driver);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drivers;
    }
}