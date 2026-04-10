package com.hotel.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private String id;
    private String customerName;
    private Room room;
    private int nights;
    private String status; // "CREATED", "CHECKED_IN", "CHECKED_OUT"
    private List<AdditionalService> services;
    private String accessKey;

    public Reservation(String id, String customerName, Room room, int nights, String accessKey) {
        this.id = id;
        this.customerName = customerName;
        this.room = room;
        this.nights = nights;
        this.status = "CREATED";
        this.services = new ArrayList<>();
        this.accessKey = accessKey;
    }

    /**
     * Agrega un servicio adicional a la reserva.
     * Solo se pueden agregar servicios si la reserva está en estado CREATED o CHECKED_IN.
     */
    public void addService(AdditionalService service) {
        if ("CHECKED_OUT".equals(this.status)) {
            throw new IllegalStateException("No se pueden agregar servicios a una reserva con estado CHECKED_OUT.");
        }
        this.services.add(service);
    }

    /**
     * Cambia el estado a CHECKED_IN.
     * Solo válido si el estado actual es CREATED.
     */
    public void checkIn() {
        if (!"CREATED".equals(this.status)) {
            throw new IllegalStateException(
                    "No se puede hacer CHECK_IN. Estado actual: " + this.status + ". Se requiere: CREATED."
            );
        }
        this.status = "CHECKED_IN";
    }

    /**
     * Cambia el estado a CHECKED_OUT.
     * Solo válido si el estado actual es CHECKED_IN.
     */
    public void checkOut() {
        if (!"CHECKED_IN".equals(this.status)) {
            throw new IllegalStateException(
                    "No se puede hacer CHECK_OUT. Estado actual: " + this.status + ". Se requiere: CHECKED_IN."
            );
        }
        this.status = "CHECKED_OUT";
    }

    // Getters y Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<AdditionalService> getServices() { return services; }
    public void setServices(List<AdditionalService> services) { this.services = services; }

    public String getAccessKey() { return accessKey; }
    public void setAccessKey(String accessKey) { this.accessKey = accessKey; }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", room=" + room +
                ", nights=" + nights +
                ", status='" + status + '\'' +
                ", services=" + services +
                ", accessKey='" + accessKey + '\'' +
                '}';
    }
}