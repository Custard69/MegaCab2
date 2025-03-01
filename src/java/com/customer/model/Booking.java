package com.customer.model;

public class Booking {
    private int bookingId;
    private int customerId;
    private String pickupLocation;
    private String destination;
    private String carType;
    private int distance;
    private double fare;
    private String status; // Pending, Assigned, Completed
    private String driverName; // New field
    private String driverPhone; // New field

    // Constructor without driver details
    public Booking(int customerId, String pickupLocation, String destination, String carType, int distance, double fare, String status) {
        this.customerId = customerId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.carType = carType;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
    }

    // Overloaded constructor with bookingId (existing)
    public Booking(int bookingId, int customerId, String pickupLocation, String destination, String carType, int distance, double fare, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.pickupLocation = pickupLocation;
        this.destination = destination;
        this.carType = carType;
        this.distance = distance;
        this.fare = fare;
        this.status = status;
    }

    

    // Getters and Setters
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public double getFare() { return fare; }
    public void setFare(double fare) { this.fare = fare; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getDriverPhone() { return driverPhone; }
    public void setDriverPhone(String driverPhone) { this.driverPhone = driverPhone; }
}
