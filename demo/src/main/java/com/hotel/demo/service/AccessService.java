package com.hotel.demo.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccessService {

    /**
     * Genera una llave de acceso digital única para la habitación reservada.
     *
     * @return UUID aleatorio como String
     */
    public String generateKey() {
        return UUID.randomUUID().toString();
    }
}