package com.example.gestionhotel.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Worker extends Person {
    //ATTRIBUTES
    protected String function;
    protected String password;//the login is the id defined in the Person class

    //GETTERS
    public String getFunction() {
        return function;
    }
    public String getPassword() {
        return password;
    }

    //SETTERS
    public void setFunction(String function) {
        this.function = function;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //CONSTRUCTORS


    public Worker(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate, String function, String password) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
        setFunction(function);
        setPassword(password);
    }

    //METHODS
    //MANAGE CLIENTS
    public static void addClient(){}

    public static void getClient(){}

    public static void removeClient(){}

    public static void updateClient(){}

    //MANAGE ROOM
    public static void addRoom(){}

    public static void getRoom(){}

    public static void removeRoom(){}

    public static void updateRoom(){}


    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}

    public static void main(String[] args) {
    }
}
