package com.example.gestionhotel.model;

public class Transaction {
    private String id_cient;
    private String id_receptionnist;
    private String id_room;
    private String nature;
    private String date;
    private String price;

    public String getId_cient() {
        return id_cient;
    }

    public void setId_cient(String id_cient) {
        this.id_cient = id_cient;
    }

    public String getId_receptionnist() {
        return id_receptionnist;
    }

    public void setId_receptionnist(String id_receptionnist) {
        this.id_receptionnist = id_receptionnist;
    }

    public String getId_room() {
        return id_room;
    }

    public void setId_room(String id_room) {
        this.id_room = id_room;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
