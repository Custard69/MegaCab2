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
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    public static boolean saveBooking(Booking booking) {
        boolean success = false;
        
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                "INSERT INTO bookings (customer_id, pickup_location, destination, car_Type, distance, fare, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            // Retrieve details from the booking object
            int customerId = booking.getCustomerId();
            String pickupLocation = booking.getPickupLocation();
            String destination = booking.getDestination();
            String carType = booking.getCarType();
            int distance = booking.getDistance();
            double baseFare = booking.getFare(); // Base fare before applying car type multiplier

            // Apply pricing logic based on car type
            double multiplier = 1.0; // Default for Mini
            if (carType.equalsIgnoreCase("Sedan")) {
                multiplier = 1.1; // 10% increase for Sedan
            } else if (carType.equalsIgnoreCase("SUV")) {
                multiplier = 1.15; // 15% increase for SUV
            }
            
            double totalFare = baseFare * multiplier;

            // Insert values into the database
            pst.setInt(1, customerId);
            pst.setString(2, pickupLocation);
            pst.setString(3, destination);
            pst.setString(4, carType);
            pst.setInt(5, distance);
            pst.setDouble(6, totalFare);  // Updated fare with car type adjustment
            pst.setString(7, "Pending");

            success = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
    
    
    public static String getBookingStatus(int bookingId) {
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                "SELECT status FROM bookings WHERE id = ?")) {
            
            pst.setInt(1, bookingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Pending";
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
}
    
    
    

