package com.hotel.demo.model;

public class Room {

    private String type;
    private double price;
    private boolean available;

    public Room(String type, double price, boolean available) {
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Room{type='" + type + "', price=" + price + ", available=" + available + "}";
    }
}