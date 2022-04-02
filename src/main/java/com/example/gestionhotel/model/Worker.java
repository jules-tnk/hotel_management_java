package com.example.gestionhotel.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Worker extends Person {
    //CONNECTOR TO THE DATABASE
    DbConnector workerDbConnector = new DbConnector();

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

    //CONSTRUCTOR
    public Worker(String worker_id) {
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\";", worker_id);
        ResultSet result = workerDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                this.setId(result.getString("id"));
                this.setFirstName(result.getString("firstName"));
                this.setLastName(result.getString("lastName"));
                this.setEmail(result.getString("email"));
                this.setBirthDate(String.valueOf(result.getDate("birthDate")));
                this.setPhoneNumber(result.getInt("phoneNumber"));
                this.setFunction(result.getString("function"));
                this.setPassword(result.getString("password"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker("eb416362");
        System.out.println(worker.getFirstName());
        System.out.println(worker.getLastName());
        System.out.println(worker.getBirthDate());
        System.out.println(worker.getPassword());
        System.out.println(worker.getFunction());
    }
}
