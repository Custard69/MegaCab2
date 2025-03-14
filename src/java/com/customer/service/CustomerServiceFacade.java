/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.service;

import com.customer.dao.CustomerDBUtil;
import com.customer.model.Customer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerServiceFacade {
    private static final Logger LOGGER = Logger.getLogger(CustomerServiceFacade.class.getName());

    public Customer authenticateUser(String userName, String password) {
        try {
            List<Customer> customers = CustomerDBUtil.validate(userName, password);
            if (!customers.isEmpty()) {
                LOGGER.log(Level.INFO, "User authenticated successfully: {0}", userName);
                return customers.get(0); // Return the first matching customer
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during authentication", e);
        }
        return null; // Return null if authentication fails
    }
}