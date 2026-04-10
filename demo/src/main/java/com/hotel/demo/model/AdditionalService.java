package com.hotel.demo.model;

public class AdditionalService {

    private String name;
    private double price;

    public AdditionalService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "AdditionalService{name='" + name + "', price=" + price + "}";
    }
}