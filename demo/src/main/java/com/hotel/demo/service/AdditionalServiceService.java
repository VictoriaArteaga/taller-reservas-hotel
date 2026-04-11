package com.hotel.demo.service;

import com.hotel.demo.model.AdditionalService;
import org.springframework.stereotype.Service;

@Service
public class AdditionalServiceService {

    /**
     * Crea un servicio adicional según su nombre.
     *
     * Casos:
     *   "spa"       → $50
     *   "breakfast" → $15
     *   "transport" → $30
     *
     * @param name nombre del servicio (case-insensitive)
     * @return AdditionalService con nombre y precio correspondiente
     * @throws IllegalArgumentException si el servicio no existe
     */
    public AdditionalService createService(String name) {
        return switch (name.toLowerCase()) {
            case "spa"       -> new AdditionalService("spa", 50.0);
            case "breakfast" -> new AdditionalService("breakfast", 15.0);
            case "transport" -> new AdditionalService("transport", 30.0);
            default -> throw new IllegalArgumentException(
                    "Servicio no reconocido: '" + name + "'. Opciones válidas: spa, breakfast, transport.");
        };
    }
}