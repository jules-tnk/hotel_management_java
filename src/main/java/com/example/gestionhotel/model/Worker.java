package com.example.gestionhotel.model;

import java.sql.Date;

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
    public Worker() {}
    public Worker(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate, String function, String password) {
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

    public static void removeClient(String idClient){
        DbConnector.removeClient(idClient);
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

    //LOGIN
    public boolean login(String id, String password){
        boolean isRegistered = DbConnector.isWorkeRegistered(id);
        if ( isRegistered ){
            String savedPassword = DbConnector.getWorkerPassword(id);

            if (password.equals(savedPassword)){
                Worker worker = DbConnector.getWorker(id);
                setFirstName(worker.getFirstName());
                setLastName(worker.getLastName());
                setEmail(worker.getEmail());
                setId(worker.getId());
                setFunction(worker.getFunction());
                setBirthDate(worker.getBirthDate());
                setPhoneNumber(worker.getPhoneNumber());
                return true;
            } else { return false; }
        }
        else { return false; }
    }
}
