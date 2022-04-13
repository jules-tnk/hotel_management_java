package com.example.gestionhotel.model;

import java.sql.Date;

public class Admin extends Worker {
    public Admin() {
        super();
    }
    public Admin(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate, String function, String password) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
        setFunction(function);
        setPassword(password);
    }

    public Admin(String username) {
    }
}
