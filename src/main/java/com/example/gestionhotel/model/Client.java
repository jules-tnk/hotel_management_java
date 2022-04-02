package com.example.gestionhotel.model;
import java.sql.*;

public class Client extends Person {
    DbConnector clientDbConnector = new DbConnector();

    public Client(String client_id) {
        clientDbConnector.initializeConnection();
        String request = String.format("SELECT * FROM client WHERE id=\"%s\";", client_id);
        System.out.println(request);
        ResultSet result = clientDbConnector.executeRequest(request);
        //ResultSet result = clientDbConnector.executeRequest("SELECT * from client");
        try {
            while (result.next()) {
                this.id = result.getString("id");
                this.firstName = result.getString("firstName");
                this.lastName = result.getString("lastName");
                this.email = result.getString("email");
                this.birthDate = result.getDate("birthDate");
                this.phoneNumber = result.getInt("phoneNumber");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("tl589621");
        System.out.println(client.getFirstName());
    }
}
