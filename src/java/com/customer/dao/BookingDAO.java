/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.dao;


import com.customer.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author xevon
 */
public class BookingDAO {
    
       public static boolean saveBooking(Booking booking) {
        boolean success = false;
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(
                "INSERT INTO bookings (customer_id, pickup_location, destination, car_Type, distance, fare, status) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            pst.setInt(1, booking.getCustomerId());
            pst.setString(2, booking.getPickupLocation());
            pst.setString(3, booking.getDestination());
            pst.setString(4, booking.getCarType());
            pst.setInt(5, booking.getDistance());
            pst.setDouble(6, booking.getFare());
            pst.setString(7, "Pending");

            success = pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
    
}
