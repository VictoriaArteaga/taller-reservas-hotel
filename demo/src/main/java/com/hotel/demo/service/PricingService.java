package com.hotel.demo.service;

import com.hotel.demo.model.Room;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    /**
     * Calcula el costo total de la habitación según temporada.
     *
     * Lógica:
     *   base = room.price * nights
     *   si highSeason → base * 1.5
     *   si no         → base
     *
     * @param room        Habitación reservada
     * @param nights      Número de noches
     * @param highSeason  true si es temporada alta
     * @return Costo total de la habitación
     */
    public double calculate(Room room, int nights, boolean highSeason) {
        double base = room.getPrice() * nights;
        return highSeason ? base * 1.5 : base;
    }
}