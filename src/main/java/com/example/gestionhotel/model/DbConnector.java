package com.example.gestionhotel.model;

import java.sql.*;

public abstract class DbConnector {
    //ATTRIBUTES
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private String databaseUser = "root";
    private String databasePassword = "";

    private Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet ;

    public static ResultSet executeRequest(String request){
        try {
            resultSet = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return resultSet;
    }

    public DbConnector() {
        try{ Class.forName(JDBC_DRIVER);}
        catch (ClassNotFoundException e){ e.printStackTrace(); }

        try {
            connection = DriverManager.getConnection(DATABASE_URL, databaseUser, databasePassword);
            statement = connection.createStatement();
        } catch (SQLException e) { e.printStackTrace(); }

    }

    //MANAGE WORKERS
        //lOGIN
    public static String getWorkerPassword(String worker_id){
        String request = "";
        String password = "";
        resultSet = executeRequest(request);
        try {
            password = resultSet.getString("password");
        } catch (SQLException e) { e.printStackTrace(); }
        return password;
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
