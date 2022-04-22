package com.example.gestionhotel.model;

import javafx.collections.ObservableList;

import java.sql.Date;

public class Receptionist extends Worker {
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
    public Receptionist(String receptionistId) {
        Receptionist receptionist = DbConnector.getReceptionist(receptionistId);
        this.setFirstName( receptionist.getFirstName() );
        this.setLastName( receptionist.getLastName() );
        this.setBirthDate( receptionist.getBirthDate() );
        this.setFunction( receptionist.getFunction() );
        this.setId( receptionist.getId() );
        this.setEmail( receptionist.getEmail() );
        this.setBirthDate( receptionist.getBirthDate() );

    }
    public Receptionist() {
        super();
    }
    public Receptionist(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate, String function, String password) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
        setFunction(function);
        setPassword(password);
    }

    //METHODS
    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}

    public ObservableList<Room> getRoomsByTags(double minPrice, double maxPrice, String type, Boolean isAvailable) {
        return DbConnector.getRoomsByTags(minPrice, maxPrice, type, isAvailable);
    }

    public boolean isClientRegistered(String clientId) {
        return DbConnector.isClientRegistered(clientId);
    }

    public boolean isRoomRegistered(String roomId) {
        return DbConnector.isRoomRegistered(roomId);
    }

    public boolean isRoomAvailable(String roomId) {
        return DbConnector.isRoomAvailable(roomId);
    }

    public boolean addReservation(Transaction reservation) {
        return DbConnector.addReservation(reservation);
    }

    public int getReservationDuration(int idTransaction) {
        return DbConnector.getReservationDuration(idTransaction);
    }

    public double getRoomPrice(String roomId) {
        return DbConnector.getRoomPrice(roomId);
    }

    public Transaction getClientLastTransaction(String clientId) {
        return DbConnector.getClientLastTransaction(clientId);
    }

    public boolean addLiberation(Transaction liberation) {
        return DbConnector.addLiberation(liberation);
    }
}
