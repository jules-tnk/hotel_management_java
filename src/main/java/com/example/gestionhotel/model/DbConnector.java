package com.example.gestionhotel.model;

import java.sql.*;

public class DbConnector {
    //ATTRIBUTES
    //private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    //private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://sql11.freemysqlhosting.net/sql11483850";
    private String databaseUser = "sql11483850";
    private String databasePassword = "ErgYgPmb4i";

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

    public DbConnector() {
        try{ Class.forName(this.JDBC_DRIVER);}
        catch (ClassNotFoundException e){ e.printStackTrace(); }

        try {
            this.connection = DriverManager.getConnection(this.DATABASE_URL, databaseUser, databasePassword);
            this.statement = this.connection.createStatement();
        } catch (SQLException e) { e.printStackTrace(); }

    }
}
