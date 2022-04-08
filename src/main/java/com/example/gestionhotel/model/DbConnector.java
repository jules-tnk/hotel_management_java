package com.example.gestionhotel.model;

import java.sql.*;

public class DbConnector {
    //ATTRIBUTES
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private String databaseUser = "root";
    private String databasePassword = "";

    private Connection connection = null;
    private static Statement statement = null;
    private static String request;
    private static ResultSet resultSet ;

    public static ResultSet executeQueryRequest(String request){
        ResultSet result;
        try {
            result = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    public DbConnector(){
        try{ Class.forName(JDBC_DRIVER);}
        catch (ClassNotFoundException e){ e.printStackTrace(); }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, databaseUser, databasePassword);
            statement = connection.createStatement();
        } catch (SQLException e) { e.printStackTrace(); }

    }

    //MANAGE WORKERS
        //lOGIN
    public static boolean isWorkeRegistered(String worker_id){
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\"", worker_id);
        ResultSetMetaData metaData;
        resultSet = executeQueryRequest(request);
        return (resultSet != null);
    }

    public static String getWorkerPassword(String worker_id){
        String request = String.format("SELECT password FROM worker WHERE id=\"%s\"", worker_id);
        String password = "";
        resultSet = executeQueryRequest(request);
        try {
            while ( resultSet.next() ){
                password = resultSet.getString("password");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return password;
    }

    public static Worker getWorker(String worker_id) {
        request = String.format("SELECT * FROM worker WHERE id=\"%s\"", worker_id);
        resultSet = executeQueryRequest(request);
        Worker worker = new Worker();
        try {
            while ( resultSet.next() ){
                worker.setLastName( resultSet.getString("lastName") );
                worker.setFirstName( resultSet.getString("firstName") );
                worker.setFunction( resultSet.getString("function") );
                worker.setId( resultSet.getString("id") );
                worker.setEmail( resultSet.getString("email") );
                worker.setPhoneNumber( resultSet.getInt("phoneNumber") );
                worker.setBirthDate( resultSet.getDate("birthDate") );
            }
            return worker;
        } catch (SQLException e){ e.printStackTrace(); return null;}
    }

    //MANAGE CLIENTS
    public static void addClient(){}

    public static void getClient(){}

    public static void removeClient(){}

    public static void updateClient(){}

    //MANAGE ROOM
    public static void addRoom(){}

    public static void getRoom(){}

    public static void removeRoom(){}

    public static void updateRoom(){}


    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}

}
