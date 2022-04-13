package com.example.gestionhotel.model;

import java.sql.Date;

public class Worker extends Person {
    //ATTRIBUTES
    protected String function;

    public Worker(String id) {
        Worker worker = DbConnector.getWorker(id);
        //series of setters
    }

    //GETTERS

    //SETTERS
    public String getFunction() { return function; }

    public void setFunction(String function) { this.function = function; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    protected String password;
    public Worker(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
    }

    public Worker() {

    }

}
