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
    
    private Connection connection;

    // Constructor to inject a connection (for testing)
    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    // Default constructor for real usage
    public BookingDAO() throws SQLException {
        this.connection = DatabaseUtil.getConnection(); // Keep real connection for non-test cases
    }

    
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
    
    
            public static List<Booking> getOngoingTrips() {
            List<Booking> trips = new ArrayList<>();
            try (Connection con = DatabaseUtil.getConnection();
                 PreparedStatement pst = con.prepareStatement("SELECT * FROM bookings WHERE status = 'Assigned'");
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



       public static List<Booking> getCompletedTrips() {
        List<Booking> trips = new ArrayList<>();
        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT * FROM bookings WHERE status = 'Completed'");
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
    
   
    
    
        public static Booking getBookingById(int bookingId) {
        Booking booking = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT b.*, d.name AS driverName, d.phone AS driverPhone, d.licenseNumber AS carNumber, " +
                         "c.name AS customerName FROM bookings b " +
                         "LEFT JOIN drivers d ON b.driver_id = d.id " +
                         "LEFT JOIN customer c ON b.customer_id = c.id " +
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
                // Set additional details
                booking.setDriverName(rs.getString("driverName"));
                booking.setDriverPhone(rs.getString("driverPhone"));
                booking.setCarNumber(rs.getString("carNumber"));
                booking.setCustomerName(rs.getString("customerName")); // Set customer name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources if necessary
        }

        return booking;
    }

        
        
//   DriverTrip Stuff
        
       public static Booking getCurrentTrip(int driverId) {
     Booking booking = null;
     String query = "SELECT * FROM bookings WHERE driver_id = ? AND (status = 'Assigned' OR status = 'Driver is on the way') LIMIT 1";
     
     try (Connection conn = DatabaseUtil.getConnection(); 
          PreparedStatement stmt = conn.prepareStatement(query)) {
          
         stmt.setInt(1, driverId);
         ResultSet rs = stmt.executeQuery();
         
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
             // Debugging: Print out the current status
             System.out.println("Booking ID: " + booking.getBookingId() + ", Status: " + booking.getStatus());
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return booking;
 }



    // Mark the trip as completed and update the booking status
    public static boolean completeTrip(int bookingId) {
    boolean success = false;
    String updateBookingQuery = "UPDATE bookings SET status = 'Completed' WHERE booking_id = ?";
    String updateDriverQuery = "UPDATE drivers SET status = 'Available' WHERE id = (SELECT driver_id FROM bookings WHERE booking_id = ?)";

    try (Connection conn = DatabaseUtil.getConnection()) {
        conn.setAutoCommit(false); // Start transaction

        try (PreparedStatement bookingStmt = conn.prepareStatement(updateBookingQuery);
             PreparedStatement driverStmt = conn.prepareStatement(updateDriverQuery)) {

            bookingStmt.setInt(1, bookingId);
            int bookingRowsAffected = bookingStmt.executeUpdate();

            driverStmt.setInt(1, bookingId); // Fetch driver_id from bookings table
            int driverRowsAffected = driverStmt.executeUpdate();

            if (bookingRowsAffected > 0 && driverRowsAffected > 0) {
                conn.commit();
                success = true;
                System.out.println("Trip completed: Booking ID " + bookingId);
            } else {
                conn.rollback();
                System.err.println("Failed to complete trip. Booking Rows: " + bookingRowsAffected + ", Driver Rows: " + driverRowsAffected);
            }
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return success;
}


    // Get the driverId associated with a booking
    public static int getDriverIdForBooking(int bookingId) {
        int driverId = -1;
        String query = "SELECT driver_id FROM bookings WHERE booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                driverId = rs.getInt("driver_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driverId;
    }
            
        public static List<Booking> getCompletedTrips(int driverId) {
        List<Booking> trips = new ArrayList<>();
        String query = "SELECT booking_id, pickup_location, destination, fare, status, booking_date FROM bookings WHERE driver_id = ? AND status = 'Completed' ORDER BY booking_date DESC";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, driverId);
            System.out.println("Executing query for driver ID: " + driverId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Trip Found: " + rs.getInt("booking_id") + " - " + rs.getString("status"));

                Booking trip = new Booking(
                    rs.getInt("booking_id"),
                    0, // No need for customerId
                    rs.getString("pickup_location"),
                    rs.getString("destination"),
                    "", // No need for carType
                    0, // No need for distance
                    rs.getDouble("fare"),
                    rs.getString("status"),
                    rs.getTimestamp("booking_date"),
                    driverId
                );
                trips.add(trip);
            }

            System.out.println("Total Completed Trips Found: " + trips.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

        
        
        
        
       
        
        public static List<Booking> getBookingsByCustomerId(int customerId) {
        List<Booking> bookings = new ArrayList<>();
        
        String query = "SELECT b.booking_id, b.customer_id, b.pickup_location, b.destination, " +
                       "b.car_type, b.distance, b.fare, b.status, b.booking_date, " +
                       "d.id AS driver_id, d.name AS driver_name, d.phone AS driver_phone " +
                       "FROM bookings b " +
                       "LEFT JOIN drivers d ON b.driver_id = d.id " +
                       "WHERE b.customer_id = ? " +
                       "ORDER BY b.booking_date DESC";

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
             
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
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

                // Set driver details if available
                if (rs.getInt("driver_id") != 0) {
                    booking.setDriverName(rs.getString("driver_name"));
                    booking.setDriverPhone(rs.getString("driver_phone"));
                }

                bookings.add(booking);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
            
            public static boolean hasActiveBooking(int customerId) {
        boolean hasActiveBooking = false;

        try (Connection con = DatabaseUtil.getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT status FROM bookings WHERE customer_id = ? AND status IN ('Pending', 'Assigned')")) {

            pst.setInt(1, customerId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                hasActiveBooking = true;  // There is an active or pending booking
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hasActiveBooking;
    }

}



    


    
       


 
    
    
    
