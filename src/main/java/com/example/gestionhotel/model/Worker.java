package com.example.gestionhotel.model;

import java.sql.Date;

public class Worker extends Person {
    //ATTRIBUTES
    protected String function;
    protected String password;



    //GETTERS
    public String getFunction() { return function; }
    public String getPassword() { return password; }

    //SETTERS
    public void setFunction(String function) { this.function = function; }
    public void setPassword(String password) { this.password = password; }


    public Worker() {}

    public Worker(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
    }

    public Worker(String id) {
        Worker worker = DbConnector.getWorker(id);
        //series of setters
    }

}
