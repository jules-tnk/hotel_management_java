package com.example.gestionhotel.model;
import java.sql.*;

public class Client extends Person {
    DbConnector clientDbConnector = new DbConnector();

    public Client(String client_id) {
        String request = String.format("SELECT * FROM client WHERE id=\"%s\";", client_id);
        ResultSet result = clientDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                this.setId(result.getString("id"));
                this.setFirstName(result.getString("firstName"));
                this.setLastName(result.getString("lastName"));
                this.setEmail(result.getString("email"));
                this.setBirthDate(String.valueOf(result.getDate("birthDate")));
                this.setPhoneNumber(result.getInt("phoneNumber"));
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
