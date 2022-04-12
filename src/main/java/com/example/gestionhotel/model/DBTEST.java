package com.example.gestionhotel.model;

public class DBTEST {
    {

    }
    public static void main(String[] args) {
        new DbConnector();
        System.out.println(DbConnector.isRoomAvailable("c32"));
    }
}
