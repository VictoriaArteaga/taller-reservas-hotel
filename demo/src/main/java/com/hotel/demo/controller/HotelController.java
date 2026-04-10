package com.hotel.demo.controller;

import com.hotel.demo.dto.ReservationDTO;
import com.hotel.demo.facade.HotelFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    private final HotelFacade hotelFacade;

    public HotelController(HotelFacade hotelFacade) {
        this.hotelFacade = hotelFacade;
    }

    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) {
        String reservationId = hotelFacade.createReservation(reservationDTO);
        return ResponseEntity.ok("Reservation created with ID: " + reservationId);
    }

    @GetMapping("/availability")
    public ResponseEntity<List<String>> getAvailability() {
        return ResponseEntity.ok(hotelFacade.getAvailability());
    }

    @PostMapping("/services/{id}")
    public ResponseEntity<String> addService(@PathVariable String id, @RequestParam String serviceType) {
        hotelFacade.addService(id, serviceType);
        return ResponseEntity.ok("Service " + serviceType + " added successfully.");
    }

    @PutMapping("/checkin/{id}")
    public ResponseEntity<String> checkIn(@PathVariable String id) {
        hotelFacade.checkIn(id);
        return ResponseEntity.ok("Check-in successful.");
    }

    @PutMapping("/checkout/{id}")
    public ResponseEntity<String> checkOut(@PathVariable String id) {
        String invoice = hotelFacade.checkOut(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping("/invoice/{id}")
    public ResponseEntity<String> getInvoice(@PathVariable String id) {
        return ResponseEntity.ok(hotelFacade.getInvoice(id));
    }
}