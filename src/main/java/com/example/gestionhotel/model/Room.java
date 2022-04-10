package com.example.gestionhotel.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    //ATTRIBUTES
    private String id;
    private double price;
    private String type;
    private boolean available;

    //GETTERS
    public String getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public boolean isAvailable() {
        return available;
    }

    //SETTERS
    public void setId(String id) {
        this.id = id;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setAvailability(boolean available) { this.available = available; }

    //CONSTRUCTOR


    public Room(String id, double price, String type, boolean available) {
        setId(id);
        setPrice(price);
        setType(type);
        setAvailability(available);
    }

    public static void main(String[] args) {
    }
}
