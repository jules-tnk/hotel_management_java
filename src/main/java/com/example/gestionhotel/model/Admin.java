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

    public Admin(String adminId) {
        Admin admin = DbConnector.getAdmin(adminId);
        this.setFirstName( admin.getFirstName() );
        this.setLastName( admin.getLastName() );
        this.setBirthDate( admin.getBirthDate() );
        this.setFunction( admin.getFunction() );
        this.setId( admin.getId() );
        this.setEmail( admin.getEmail() );
        this.setBirthDate( admin.getBirthDate() );
    }
}
