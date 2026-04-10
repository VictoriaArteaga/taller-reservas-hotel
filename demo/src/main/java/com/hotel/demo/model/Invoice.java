package com.hotel.demo.model;

import java.util.List;

public class Invoice {

    private String reservationId;
    private String customerName;
    private String roomType;
    private int nights;
    private double roomCost;
    private List<AdditionalService> services;
    private double servicesCost;
    private double total;

    public Invoice(String reservationId, String customerName, String roomType,
                   int nights, double roomCost, List<AdditionalService> services,
                   double servicesCost, double total) {
        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
        this.roomCost = roomCost;
        this.services = services;
        this.servicesCost = servicesCost;
        this.total = total;
    }

    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public int getNights() { return nights; }
    public void setNights(int nights) { this.nights = nights; }

    public double getRoomCost() { return roomCost; }
    public void setRoomCost(double roomCost) { this.roomCost = roomCost; }

    public List<AdditionalService> getServices() { return services; }
    public void setServices(List<AdditionalService> services) { this.services = services; }

    public double getServicesCost() { return servicesCost; }
    public void setServicesCost(double servicesCost) { this.servicesCost = servicesCost; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "Invoice{" +
                "reservationId='" + reservationId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", roomType='" + roomType + '\'' +
                ", nights=" + nights +
                ", roomCost=" + roomCost +
                ", servicesCost=" + servicesCost +
                ", total=" + total +
                '}';
    }
}