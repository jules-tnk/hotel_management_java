package com.example.gestionhotel.model;

import java.sql.*;

public class DbConnector {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";
    private String databaseUser = "root";
    private String databasePassword = "";

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet ;

    public ResultSet executeRequest(String request){
        try {
            this.resultSet = this.statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return this.resultSet;
    }

    public boolean initializeConnection() {
        try{ Class.forName(this.JDBC_DRIVER);}
        catch (ClassNotFoundException ex){ return  false; }

        try {
            this.connection = DriverManager.getConnection(this.DATABASE_URL, databaseUser, databasePassword);
            this.statement = this.connection.createStatement();
        } catch (SQLException ex) { return false; }

        return true;
    }
}
