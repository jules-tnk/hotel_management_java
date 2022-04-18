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
    //MANAGE CLIENTS
    public static boolean addClient(Client client){
        boolean isClientAdded = DbConnector.addClient(client);
        return isClientAdded;
    }

    public static void getClient(){}

    public static boolean removeClient(String idClient){
        boolean isClientRemoved =  DbConnector.removeClient(idClient);
        return isClientRemoved;
    }

    public static void updateClient(Client client){
        DbConnector.updateClient(client);
    }

    //MANAGE ROOM
    public static void addRoom(Room room){}

    public static void getRoom(){}

    public static boolean setRoomAvailability(String roomId, boolean availability){
        boolean isAvailabilityUpdated = DbConnector.setRoomAvailability(roomId, availability);
        return isAvailabilityUpdated;
    }

    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}

    public ObservableList<Room> getRoomsByTags(double minPrice, double maxPrice, String type, Boolean isAvailable) {
        return DbConnector.getRoomsByTags(minPrice, maxPrice, type, isAvailable);
    }

    public ObservableList<Client> getClientsById(String clientId) {
        return DbConnector.getClientsById(clientId);
    }

    public ObservableList<Client> getClientsByFirstName(String firstName) {
        return DbConnector.getClientsByFirstName(firstName);
    }

    public ObservableList<Client> getClientsByLastName(String lastName) {
        return DbConnector.getClientsByLastName(lastName);
    }

    public ObservableList<Client> getClientsByEmail(String email) {
        return DbConnector.getClientsByEmail(email);
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
