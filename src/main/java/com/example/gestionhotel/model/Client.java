package com.example.gestionhotel.model;
import java.sql.*;

public class Client extends Person {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/cabsbooking";

    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet ;
    static ResultSetMetaData metaData ;
    String resultat = "";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM booking");
            metaData = resultSet.getMetaData();
            System.out.println(metaData);
            System.out.println("Sucess!!!!");
    }
}
