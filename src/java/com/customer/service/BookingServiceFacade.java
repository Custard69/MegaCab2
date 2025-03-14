/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customer.service;

import com.customer.dao.BookingDAO;
import com.customer.dao.driver.DriverDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingServiceFacade {
    private static final Logger LOGGER = Logger.getLogger(BookingServiceFacade.class.getName());

    public boolean assignDriver(int bookingId, int driverId) {
        try {
            boolean assigned = BookingDAO.assignDriverToTrip(bookingId, driverId);
            if (assigned) {
                DriverDAO.updateDriverStatus(driverId, "Busy"); // Mark driver as busy
                LOGGER.log(Level.INFO, "Driver {0} assigned to booking {1}", new Object[]{driverId, bookingId});
                return true;
            } else {
                LOGGER.log(Level.WARNING, "Failed to assign driver {0} to booking {1}", new Object[]{driverId, bookingId});
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error while assigning driver", e);
        }
        return false;
    }
}