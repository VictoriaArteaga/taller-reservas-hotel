// Patron facade.

package com.hotel.demo.facade;

import com.hotel.demo.dto.ReservationDTO;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class HotelFacade {

    private final Map<String, String> mockReservations = new HashMap<>();

    public String createReservation(ReservationDTO reservationDTO) {
        String id = UUID.randomUUID().toString();
        mockReservations.put(id, "Confirmed");
        return id;
    }

    public void addService(String reservationId, String serviceType) {
        System.out.println("Service " + serviceType + " added to reservation " + reservationId);
    }

    public void checkIn(String reservationId) {
        String digitalKey = UUID.randomUUID().toString();
        System.out.println("Check-in complete for " + reservationId + ". Key: " + digitalKey);
    }

    public String checkOut(String reservationId) {
        return "Invoice for " + reservationId + ": Total $450.00 (Includes Room + Spa)";
    }

    public List<String> getAvailability() {
        return Arrays.asList("Single Room", "Double Room", "Suite");
    }

    public String getInvoice(String reservationId) {
        return "Detailed Invoice for " + reservationId + " - Status: Paid";
    }
}