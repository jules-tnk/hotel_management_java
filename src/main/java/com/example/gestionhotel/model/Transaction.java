package com.example.gestionhotel.model;

import java.sql.Date;

public class Transaction {
    //ATTRIBUTES
    private int idTransaction;
    private String idClient;
    private String idReceptionist;
    private String idRoom;
    private String nature;
    private Date date;
    private double price;

    public Transaction() {

    }

    //private int numberOfDays;

    //GETTERS
    public String getIdClient() {
        return idClient;
    }
    public String getIdReceptionist() {
        return idReceptionist;
    }
    public String getIdRoom() {
        return idRoom;
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
    public int getIdTransaction() { return idTransaction; }

    //SETTERS
    public void setIdClient(String idClient) {this.idClient = idClient; }
    public void setIdReceptionist(String idReceptionist) { this.idReceptionist = idReceptionist; }
    public void setIdRoom(String idRoom) { this.idRoom = idRoom; }
    public void setNature(String nature) { this.nature = nature; }
    public void setDate(Date date) { this.date = date; }
    public void setPrice(double price) { this.price = price; }
    public void setIdTransaction(int idTransaction) { this.idTransaction = idTransaction; }

    //CONSTRUCTORS
    public Transaction(int idTransaction, String idClient, String idReceptionist, String idRoom, String nature, Date date, double price) {
        setIdClient(idClient);
        setIdReceptionist(idReceptionist);
        setIdRoom(idRoom);
        setNature(nature);
        setDate(date);
        setPrice(price);
        setIdTransaction(idTransaction);
    }

    public Transaction(String idClient, String idReceptionist, String idRoom, String nature, Date date, double price) {
        setIdClient(idClient);
        setIdReceptionist(idReceptionist);
        setIdRoom(idRoom);
        setNature(nature);
        setDate(date);
        setPrice(price);
    }

    public Transaction(String idClient, String idReceptionist, String idRoom, String nature, Date date) {
        setIdClient(idClient);
        setIdReceptionist(idReceptionist);
        setIdRoom(idRoom);
        setNature(nature);
        setDate(date);
    }

    public Transaction(String idClient, String idReceptionist, String idRoom, String nature) {
        setIdClient(idClient);
        setIdReceptionist(idReceptionist);
        setIdRoom(idRoom);
        setNature(nature);
    }

    public Transaction(String clientId, String receptionistId, String roomId, String nature, double price) {
        setIdClient(clientId);
        setIdReceptionist(receptionistId);
        setIdRoom(roomId);
        setNature(nature);
        setPrice(price);
    }

}
