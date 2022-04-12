package com.example.gestionhotel.model;

import java.sql.*;

public class DbConnector {
    //ATTRIBUTES
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DATABASE_URL = "jdbc:mysql://localhost/hotelmanagementdb";

    private final String databaseUser = "root";
    private final String databasePassword = "";

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

    public static int executeUpdateRequest(String request){
        int result;
        try {
            result = statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
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
    public static boolean isWorkerRegistered(String worker_id){
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\"", worker_id);
        ResultSetMetaData metaData;
        resultSet = executeQueryRequest(request);
        try {
            if ( resultSet.next() ){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
        request = String.format("SELECT * FROM receptionist WHERE id=\"%s\"", worker_id);
        resultSet = executeQueryRequest(request);
        Receptionist receptionist = new Receptionist();
        try {
            while ( resultSet.next() ){
                receptionist.setLastName( resultSet.getString("lastName") );
                receptionist.setFirstName( resultSet.getString("firstName") );
                receptionist.setFunction( resultSet.getString("function") );
                receptionist.setId( resultSet.getString("id") );
                receptionist.setEmail( resultSet.getString("email") );
                receptionist.setPhoneNumber( resultSet.getInt("phoneNumber") );
                receptionist.setBirthDate( resultSet.getDate("birthDate") );
            }
            return receptionist;
        } catch (SQLException e){ e.printStackTrace(); return null;}
    }

    //MANAGE CLIENTS
    public static void addClient(Client client){
        request = String.format("INSERT INTO client (id, firstName, lastName, phoneNumber, email, birthDate) VALUES(\"%s\",\"%s\",\"%s\",%s,\"%s\",\"%s\")",
                client.getId(), client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getBirthDate());
        executeUpdateRequest(request);
    }

    public static boolean isClientRegistered(Client clientId){
        request = String.format("SELECT * FROM client WHERE id=\"%s\"", clientId);
        resultSet = executeQueryRequest(request);
        try {
            if (resultSet.next()){ return true; } else { return false; }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getClient(){}

    public static boolean removeClient(String idClient){
        return false;
    }

    public static void updateClient(Client client){}

    //MANAGE ROOM
    public static boolean isRoomAvailable(String roomId){
        request = String.format("SELECT available FROM room WHERE id=\"%s\"", roomId);
        resultSet = executeQueryRequest(request);
        boolean isAvailable;
        try {
            if ( resultSet.next() ){
                isAvailable = resultSet.getBoolean("available");
                return isAvailable;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getRoom(){}

    public static void removeRoom(){}

    public static void setRoomAvailability (){}


    //MANAGE TRANSACTIONS
    public static void addTransaction(){}

    public static void getTransaction(){}

    public static void removeTransaction(){}

    public static void updateTransaction(){}

}
