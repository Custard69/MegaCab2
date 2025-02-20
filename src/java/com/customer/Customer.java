package com.customer;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String phone;
    private String userName;
    private String password;
    private String role;

    public Customer(int id, String name, String email,String NIC, String phone, String userName, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.email = NIC;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    
    public String getNIC() {
        return NIC;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return role;
    }
    
    
}
