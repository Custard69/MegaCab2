package com.customer.service;

import com.customer.dao.driver.DriverDAO;
import com.customer.model.driver.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverServiceFacade {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceFacade.class.getName());

    public boolean registerDriver(String name, String email, String phone, String carType, String licenseNumber, String password) {
        try {
            // Creating Driver object with existing constructor
            Driver driver = new Driver(0, name, email, phone, carType, licenseNumber, password, "Available");

            boolean isAdded = DriverDAO.addDriver(driver);
            
            if (isAdded) {
                LOGGER.log(Level.INFO, "Driver {0} registered successfully.", name);
            } else {
                LOGGER.log(Level.WARNING, "Failed to register driver: {0}", name);
            }
            return isAdded;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error registering driver", e);
            return false;
        }
    }
    
    public boolean deleteDriver(int driverId) {
        try {
            boolean isDeleted = DriverDAO.deleteDriver(driverId);
            if (isDeleted) {
                LOGGER.log(Level.INFO, "Driver {0} deleted successfully.", driverId);
            } else {
                LOGGER.log(Level.WARNING, "Failed to delete driver {0}", driverId);
            }
            return isDeleted;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting driver", e);
            return false;
        }
    }
}
