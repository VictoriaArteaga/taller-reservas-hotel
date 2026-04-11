package com.hotel.demo.service;

import com.hotel.demo.model.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    // Lista en memoria con al menos 15 habitaciones predefinidas
    private final List<Room> rooms = new ArrayList<>();

    public RoomService() {
        initRooms();
    }

    /**
     * Inicializa las habitaciones en memoria:
     * - 5 SINGLE  @ $100
     * - 5 DOUBLE  @ $150
     * - 5 SUITE   @ $300
     */
    private void initRooms() {
        for (int i = 0; i < 5; i++) {
            rooms.add(new Room("SINGLE", 100.0, true));
            rooms.add(new Room("DOUBLE", 150.0, true));
            rooms.add(new Room("SUITE",  300.0, true));
        }
    }

    /**
     * Busca la primera habitación disponible del tipo solicitado,
     * la marca como no disponible y la retorna.
     *
     * @param type  "SINGLE", "DOUBLE" o "SUITE"
     * @return Room disponible
     * @throws IllegalArgumentException si no hay habitaciones disponibles del tipo indicado
     */
    public Room getAvailableRoom(String type) {
        return rooms.stream()
                .filter(r -> r.getType().equalsIgnoreCase(type) && r.isAvailable())
                .findFirst()
                .map(r -> {
                    r.setAvailable(false);
                    return r;
                })
                .orElseThrow(() -> new IllegalArgumentException(
                        "No hay habitaciones disponibles de tipo: " + type));
    }

    /**
     * Libera una habitación (la vuelve disponible).
     * Útil al cancelar una reserva o al hacer checkout.
     */
    public void releaseRoom(Room room) {
        room.setAvailable(true);
    }

    public List<Room> getAllRooms() {
        return rooms;
    }
}