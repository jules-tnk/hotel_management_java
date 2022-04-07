package com.example.gestionhotel.model;

import java.sql.*;

public class DbConnector {
    //ATTRIBUTES
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private String databaseUser = "root";
    private String databasePassword = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet ;

    public ResultSet executeRequest(String request){
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
}
