package com.example.gestionhotel.model;
import java.sql.*;

public class Client extends Person {
    //CONSTRUCTORS
    public Client(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate) {
        super(firstName, lastName, id, email, phoneNumber, birthDate);
    }

    public Client(){
        super();
    }
}
