/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.dao;

import com.customer.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDAO {
    
    
    // Assuming you have a connection setup method
    public static boolean saveBooking(Booking booking) {
        String query = "INSERT INTO bookings (customer_id, pickup_location, destination, car_type, distance, fare, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, booking.getCustomerId());
            pst.setString(2, booking.getPickupLocation());
            pst.setString(3, booking.getDestination());
            pst.setString(4, booking.getCarType());
            pst.setInt(5, booking.getDistance());
            pst.setDouble(6, booking.getFare());
            pst.setString(7, booking.getStatus());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    booking.setBookingId(rs.getInt(1)); // Set generated booking ID
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    
    public static String getBookingStatus(int bookingId) {
        String status = "Pending"; // Default status

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                    "SELECT status FROM bookings WHERE booking_id = ?")) {

            pst.setInt(1, bookingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                status = rs.getString("status");
            }

            System.out.println("Database query - Booking ID: " + bookingId + " - Status: " + status); // Debugging output
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return status;
}
    
    
    

    



    
    
    public static List<Booking> getPendingTrips() {
        List<Booking> trips = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM bookings WHERE status = 'Pending'");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                trips.add(new Booking(
                    rs.getInt("booking_id"),
                    rs.getInt("customer_id"),
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    rs.getString("car_Type"),
                    rs.getInt("distance"),
                    rs.getDouble("fare"),
                    rs.getString("status")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trips;
    }
    
    public static boolean assignDriverToTrip(int bookingId, int driverId) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                "UPDATE bookings SET driver_id = ?, status = 'Assigned' WHERE booking_id = ?")) {

            pst.setInt(1, driverId);
            pst.setInt(2, bookingId);

            success = pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
    
    public static Map<String, Object> getBookingDetails(int bookingId) {
        Map<String, Object> bookingDetails = new HashMap<>();

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "SELECT b.booking_id, b.customer_id, b.pickup_location, b.destination, " +
                 "b.car_type, b.distance, b.fare, b.status, d.name AS driver_name, d.phone AS driver_phone " +
                 "FROM bookings b " +
                 "LEFT JOIN drivers d ON b.driver_id = d.id " +
                 "WHERE b.booking_id = ?")) {

            pst.setInt(1, bookingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                bookingDetails.put("bookingId", rs.getInt("booking_id"));
                bookingDetails.put("customerId", rs.getInt("customer_id"));
                bookingDetails.put("pickupLocation", rs.getString("pickup_location"));
                bookingDetails.put("destination", rs.getString("destination"));
                bookingDetails.put("carType", rs.getString("car_type"));
                bookingDetails.put("distance", rs.getInt("distance"));
                bookingDetails.put("fare", rs.getDouble("fare"));
                bookingDetails.put("status", rs.getString("status"));
                bookingDetails.put("driverName", rs.getString("driver_name"));
                bookingDetails.put("driverPhone", rs.getString("driver_phone"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingDetails;
    }
    
    
        public static Booking getBookingById(int bookingId) {
           Booking booking = null;
           Connection conn = null;
           PreparedStatement stmt = null;
           ResultSet rs = null;

           try {
               conn = DatabaseUtil.getConnection();
               String sql = "SELECT b.*, d.name AS driverName, d.phone AS driverPhone, d.licenseNumber AS carNumber " +
                            "FROM bookings b LEFT JOIN drivers d ON b.driver_id = d.id " +
                            "WHERE b.booking_id = ?";
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, bookingId);
               rs = stmt.executeQuery();

               if (rs.next()) {
                   booking = new Booking(
                       rs.getInt("booking_id"),
                       rs.getInt("customer_id"),
                       rs.getString("pickup_location"),
                       rs.getString("destination"),
                       rs.getString("car_type"),
                       rs.getInt("distance"),
                       rs.getDouble("fare"),
                       rs.getString("status")
                   );
                   // Set driver details
                   booking.setDriverName(rs.getString("driverName"));
                   booking.setDriverPhone(rs.getString("driverPhone"));
                   booking.setCarNumber(rs.getString("carNumber")); // New field for car number
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
       //        DatabaseUtil.close(conn, stmt, rs);
           }

           return booking;
       }
        
        
//   DriverTrip Stuff
        
        public static Booking getCurrentTrip(int driverId) {
            Booking booking = null;
            try (Connection con = DatabaseUtil.getConnection();
                 PreparedStatement pst = con.prepareStatement(
                     "SELECT * FROM bookings WHERE driver_id = ? AND status = 'Assigned' LIMIT 1")) {

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


    public static boolean completeTrip(int bookingId) {
        String query = "UPDATE bookings SET status = 'Completed' WHERE booking_id = ?";
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, bookingId);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    
       public static Booking getBookingForDriver(int bookingId) {
            Booking booking = null;
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(
                     "SELECT b.*, d.name AS driverName, d.phone AS driverPhone, d.licenseNumber AS carNumber " +
                     "FROM bookings b LEFT JOIN drivers d ON b.driver_id = d.id " +
                     "WHERE b.booking_id = ?")) {

                stmt.setInt(1, bookingId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    System.out.println("Debug: Booking found in database.");
                    System.out.println("Debug: Status = " + rs.getString("status"));
                    System.out.println("Debug: Driver Name = " + rs.getString("driverName"));

                    booking = new Booking(
                        rs.getInt("booking_id"),
                        rs.getInt("customer_id"),
                        rs.getString("pickup_location"),
                        rs.getString("destination"),
                        rs.getString("car_type"),
                        rs.getInt("distance"),
                        rs.getDouble("fare"),
                        rs.getString("status")
                    );
                    booking.setDriverName(rs.getString("driverName"));
                    booking.setDriverPhone(rs.getString("driverPhone"));
                    booking.setCarNumber(rs.getString("carNumber"));
                } else {
                    System.out.println("Debug: No booking found with ID: " + bookingId);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return booking;
        }


 }
    
    
    
