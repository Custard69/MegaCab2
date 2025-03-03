package com.customer.dao.driver;

import com.customer.model.driver.Driver;
import com.customer.dao.DatabaseUtil;
import com.customer.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    
    // Add New Driver
    public static boolean addDriver(Driver driver) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("INSERT INTO drivers (name, email, phone, carType, licenseNumber, password, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            
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

    // Authenticate Driver
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

    // Get Available Drivers by Car Type
    public static List<Driver> getAvailableDrivers(String carType) {
        List<Driver> drivers = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM drivers WHERE status = 'Available' AND carType = ?")) {

            pst.setString(1, carType);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                drivers.add(new Driver(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("carType"),
                    rs.getString("licenseNumber"),
                    rs.getString("password"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drivers;
    }

    // Update Driver Status
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

    // Get the Current Assigned Trip for a Driver
    public static Booking getCurrentTrip(int driverId) {
        Booking booking = null;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM bookings WHERE driver_id = ? AND (status = 'Assigned' OR status = 'Driver is on the way') LIMIT 1")) {

            pst.setInt(1, driverId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getString("car_type"),
                    rs.getInt("distance"),
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    rs.getTimestamp("booking_date"),
                    rs.getInt("driver_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    // Mark a Trip as Completed
    public static boolean completeTrip(int bookingId) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE bookings SET status = 'Completed' WHERE booking_id = ?")) {

            pst.setInt(1, bookingId);
            success = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    // Get Trip History for a Driver
    public static List<Booking> getTripHistory(int driverId) {
        List<Booking> tripHistory = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM bookings WHERE driver_id = ? AND status = 'Completed' ORDER BY booking_date DESC")) {

            pst.setInt(1, driverId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                tripHistory.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getString("car_type"),
                    rs.getInt("distance"),
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    rs.getTimestamp("booking_date"),
                    rs.getInt("driver_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripHistory;
    }

    // Get Assigned Driver for a Booking
    public static Driver getAssignedDriver(int bookingId) {
        Driver driver = null;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "SELECT d.id, d.name, d.email, d.phone, d.carType, d.licenseNumber, d.status " +
                 "FROM drivers d " +
                 "INNER JOIN bookings b ON d.id = b.driver_id " +
                 "WHERE b.booking_id = ?")) {

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
