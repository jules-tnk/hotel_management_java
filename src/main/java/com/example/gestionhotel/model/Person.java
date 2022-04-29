package com.example.gestionhotel.model;
import java.sql.*;

public abstract class Person {
    protected String firstName;
    protected  String lastName;
    protected  String id;
    protected String email;
    protected  int phoneNumber;
    protected Date birthDate;

    //GETTERS
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getPhoneNumber() { return phoneNumber; }
    public Date getBirthDate() { return birthDate; }
    public String getEmail() { return email; }
    public String getId() { return id; }

    //SETTERS
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setId(String id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    //CONSTRUCTORS

    public Person() {

    }

    public Person(String firstName, String lastName, String id, String email, int phoneNumber, Date birthDate) {
        setFirstName(firstName);
        setLastName(lastName);
        setId(id);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
    }
}
