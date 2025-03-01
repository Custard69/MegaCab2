/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.model.driver;

/**
 *
 * @author xevon
 */
public class Driver {
    private int driverId;
    private String name;
    private String email;
    private String phone;
    private String carType;
    private String licenseNumber;
    private String password;
    private String status;  // Available / Assigned

    // Constructor
    public Driver(int driverId, String name, String email, String phone, String carType, String licenseNumber, String password, String status) {
        this.driverId = driverId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.carType = carType;
        this.licenseNumber = licenseNumber;
        this.password = password;
        this.status = status;
    }

  

    // Getters and Setters
    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
