package com.example.gestionhotel.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction {
    //CONNECTOR TO DATABASE
    DbConnector transactionDbConnector = new DbConnector();

    //ATTRIBUTES
    private String id_transaction;
    private String id_client;
    private String id_receptionist;
    private String id_room;
    private String nature;
    private String date;
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
    public String getDate() {
        return date;
    }
    public double getPrice() {
        return price;
    }
    public String getId_transaction() { return id_transaction; }

    //SETTERS
    public void setId_client(String id_cient) {
        this.id_client = id_cient;
    }
    public void setId_receptionnist(String id_receptionnist) {
        this.id_receptionist = id_receptionnist;
    }
    public void setId_room(String id_room) {
        this.id_room = id_room;
    }
    public void setNature(String nature) {
        this.nature = nature;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setId_transaction(String id_transaction) { this.id_transaction = id_transaction; }

    public Transaction(String transaction_id) {
        String request = String.format("SELECT * FROM transaction WHERE id_transaction=\"%s\";", transaction_id);
        ResultSet result = transactionDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                setId_transaction(result.getString("id_transaction"));
                setId_client(result.getString("id_client"));
                setId_room(result.getString("id_room"));
                setId_receptionnist(result.getString("id_receptionist"));
                setDate(result.getString("date"));
                setPrice(result.getDouble("price"));
                setNature(result.getString("nature"));
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
        Transaction transaction = new Transaction("p45");
        System.out.println(transaction.getNature());
    }
}
