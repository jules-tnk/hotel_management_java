package com.example.gestionhotel.model;

import java.sql.Date;

public class Worker extends Person {
    //ATTRIBUTES
    protected String function;

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

    //LOGIN
    public boolean login(String id, String password){
        boolean isRegistered = DbConnector.isWorkerRegistered(id);
        if ( isRegistered ){
            String savedPassword = DbConnector.getWorkerPassword(id);
            if (password.equals(savedPassword)){
                Worker worker = DbConnector.getWorker(id);
               /* setFirstName(receptionist.getFirstName());
                setLastName(receptionist.getLastName());
                setEmail(receptionist.getEmail());
                setId(receptionist.getId());
                setFunction(receptionist.getFunction());
                setBirthDate(receptionist.getBirthDate());
                setPhoneNumber(receptionist.getPhoneNumber());*/
                return true;
            } else { return false; }
        }
        else { return false; }
    }
}
