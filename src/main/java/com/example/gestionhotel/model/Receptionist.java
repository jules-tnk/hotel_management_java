package com.example.gestionhotel.model;

import java.sql.Date;

public class Receptionist extends Worker {
    //ATTRIBUTES
    protected String function;
    protected String password;//the login is the id defined in the Person class

    public Receptionist(String username) {
    }

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
    public Receptionist() {
        super();
    }
    public Receptionist(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate, String function, String password) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
        setFunction(function);
        setPassword(password);
    }

    //METHODS
    //MANAGE CLIENTS
    public static void addClient(Client client){
        DbConnector.addClient(client);
    }

    public static void getClient(){}

    public static boolean removeClient(String idClient){
        boolean isClientRemoved =  DbConnector.removeClient(idClient);
        if ( isClientRemoved == true ){ return true; }
        else { return false; }
    }

    public static void updateClient(Client client){
        DbConnector.updateClient(client);
    }

    //MANAGE ROOM
    public static void addRoom(Room room){}

    public static void getRoom(){}

    public static void setRoomAvailability(Room room, boolean availability){}

    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}


}
