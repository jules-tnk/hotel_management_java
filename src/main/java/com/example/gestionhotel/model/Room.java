package com.example.gestionhotel.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Room {
    //CONNECTOR TO THE DATABASE
    DbConnector roomDbConnector = new DbConnector();

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
    public void setAvailablity(boolean available) { this.available = available; }

    //CONSTRUCTOR
    public Room(String room_id) {
        String request = String.format("SELECT * FROM room WHERE id=\"%s\";", room_id);
        ResultSet result = roomDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                setId(result.getString("id"));
                setPrice(result.getDouble("price"));
                setType(result.getString("type"));
                setAvailablity(result.getBoolean("available"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //DATABASE METHODS
    public void addToDatabase(){}
    public void updateInDatabase(){}
    public void removeFromDatabase(){}

    public static void main(String[] args) {
        Room room = new Room("c32");
        System.out.println(room.getId());
        System.out.println(room.getPrice());
        System.out.println(room.isAvailable());
        System.out.println(room.getType());
    }
}
