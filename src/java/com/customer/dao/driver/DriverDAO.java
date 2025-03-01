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
    
    // Delete a Driver
        public static boolean deleteDriver(int driverId) {
            boolean success = false;
            try (Connection con = DatabaseUtil.getConnection();
                 PreparedStatement pst = con.prepareStatement("DELETE FROM drivers WHERE id = ?")) {

                pst.setInt(1, driverId);
                success = pst.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return success;
        }

        
        
                    // Update Driver Details
            public static boolean updateDriver(Driver driver) {
                boolean success = false;
                try (Connection con = DatabaseUtil.getConnection();
                     PreparedStatement pst = con.prepareStatement("UPDATE drivers SET name=?, email=?, phone=?, carType=?, licenseNumber=?, status=? WHERE id=?")) {

                    pst.setString(1, driver.getName());
                    pst.setString(2, driver.getEmail());
                    pst.setString(3, driver.getPhone());
                    pst.setString(4, driver.getCarType());
                    pst.setString(5, driver.getLicenseNumber());
                    pst.setString(6, driver.getStatus());
                    pst.setInt(7, driver.getDriverId());

                    success = pst.executeUpdate() > 0;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return success;
            }

            // Get Driver by ID
            public static Driver getDriverById(int id) {
                Driver driver = null;
                try (Connection con = DatabaseUtil.getConnection();
                     PreparedStatement pst = con.prepareStatement("SELECT * FROM drivers WHERE id=?")) {

                    pst.setInt(1, id);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        driver = new Driver(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("carType"),
                            rs.getString("licenseNumber"),
                            "",  // Password is not needed for editing
                            rs.getString("status")
                        );
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return driver;
            }
            
            
            
            public static Driver authenticateDriver(String email, String password) {
        Driver driver = null;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM drivers WHERE email=? AND password=?")) {

            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                driver = new Driver(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("carType"),
                    rs.getString("licenseNumber"),
                    rs.getString("password"),
                    rs.getString("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver;
    }
            
            
    public static List<Driver> getAvailableDrivers(String carType) {
    List<Driver> drivers = new ArrayList<>();
    try (Connection con = DatabaseUtil.getConnection();
         PreparedStatement pst = con.prepareStatement("SELECT * FROM drivers WHERE status = 'Available' AND carType = ?")) {
        
        pst.setString(1, carType);
        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                drivers.add(new Driver(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("carType"),  // Ensure correct column name
                    rs.getString("licenseNumber"),
                    rs.getString("password"),
                    rs.getString("status")
                ));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return drivers;
}

    
    
    public static boolean updateDriverStatus(int driverId, String status) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE drivers SET status = ? WHERE id = ?")) {

            pst.setString(1, status);
            pst.setInt(2, driverId);

            success = pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }


    
    public static Driver getAssignedDriver(int bookingId) {
    Driver driver = null;

    try (Connection con = DatabaseUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(
             "SELECT d.id, d.name, d.email, d.phone, d.carType, d.licenseNumber, d.status " +
             "FROM drivers d " +
             "INNER JOIN bookings b ON d.id = b.driver_id " +
             "WHERE b.booking_id = ?"
         )) {

        pst.setInt(1, bookingId);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            driver = new Driver(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("carType"),
                rs.getString("licenseNumber"),
                "", // Password not needed
                rs.getString("status")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return driver;
}

    
}