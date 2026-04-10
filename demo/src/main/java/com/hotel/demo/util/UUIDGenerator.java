package com.hotel.demo.util;

import java.util.UUID;

public class UUIDGenerator {

    private UUIDGenerator() {
        // Clase utilitaria, no instanciar
    }

    /**
     * Genera un UUID único como identificador de reserva.
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}