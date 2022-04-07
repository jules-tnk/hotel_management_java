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

    //CONSTRUCTORS
    public Worker(String worker_id) {
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\";", worker_id);
        ResultSet result = workerDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                setId(result.getString("id"));
                setFirstName(result.getString("firstName"));
                setLastName(result.getString("lastName"));
                setEmail(result.getString("email"));
                setBirthDate(String.valueOf(result.getDate("birthDate")));
                setPhoneNumber(result.getInt("phoneNumber"));
                setFunction(result.getString("function"));
                setPassword(result.getString("password"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //DATABASE METHODS
    public void addToDatabase(){}
    public void updateInDatabase(){}
    public void removeFromDatabase(){}

    //METHODS
    public boolean Login(String username, String password){
        return true;
    }

    public void addClient(/*info du client*/){
        String request = "";
        ResultSet result = workerDbConnector.executeRequest(request);
    }

    public void deleteClient(/*id du client*/){}

    public void updateClient(/*id du lient et nouvelles infos*/){}

    public void addTransaction(/*infos de la transaction*/){}

    public void updateTransaction(/*id de la transaction et nouvelles infos*/){}

    public void deleteTransaction(/*id de la transaction*/){}

    public void setRoomAvailablity(/* id de la chmabre et nouvelle infos*/){}

    public static void main(String[] args) {
        Worker worker = new Worker("eb416362");
        System.out.println(worker.getFirstName());
        System.out.println(worker.getLastName());
        System.out.println(worker.getBirthDate());
        System.out.println(worker.getPassword());
        System.out.println(worker.getFunction());
    }
}
