package com.example.gestionhotel.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    //ATTRIBUTES
    private String id_transaction;
    private String id_client;
    private String id_receptionist;
    private String id_room;
    private String nature;
    private Date date;
    private double price;
    //private int numberOfDays;

    //GETTERS
    public String getId_client() {
        return id_client;
    }
    public String getId_receptionist() {
        return id_receptionist;
    }
    public String getId_room() {
        return id_room;
    }
    public String getNature() {
        return nature;
    }
    public Date getDate() {
        return date;
    }
    public double getPrice() {
        return price;
    }
    public String getId_transaction() { return id_transaction; }

    //SETTERS
    public void setId_client(String id_cient) {this.id_client = id_cient; }
    public void setId_receptionnist(String id_receptionnist) { this.id_receptionist = id_receptionnist; }
    public void setId_room(String id_room) { this.id_room = id_room; }
    public void setNature(String nature) { this.nature = nature; }
    public void setDate(Date date) { this.date = date; }
    public void setPrice(double price) { this.price = price; }
    public void setId_transaction(String id_transaction) { this.id_transaction = id_transaction; }

    //CONSTRUCTORS
    public Transaction(String id_transaction, String id_client, String id_receptionist, String id_room, String nature, Date date, double price) {
        setId_client(id_client);
        setId_receptionnist(id_receptionist);
        setId_room(id_room);
        setNature(nature);
        setDate(date);
        setPrice(price);setId_transaction(id_transaction);
    }

    public static void main(String[] args) {
    }
}
