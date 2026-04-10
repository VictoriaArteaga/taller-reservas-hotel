package com.hotel.demo.repository;

import com.hotel.demo.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDatabase {

    // Lista en memoria de todas las reservas
    private final List<Reservation> reservations = new ArrayList<>();

    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    public Optional<Reservation> findById(String id) {
        return reservations.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst();
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations);
    }

    public void deleteById(String id) {
        reservations.removeIf(r -> r.getId().equals(id));
    }
}