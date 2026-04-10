package com.hotel.demo.dto;

import java.time.LocalDate;

public record ReservationDTO(
        String guestName,
        String roomType,
        LocalDate checkInDate,
        LocalDate checkOutDate
) {}