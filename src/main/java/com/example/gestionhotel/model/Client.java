package com.example.gestionhotel.model;
import java.sql.*;

public class Client extends Person {
    DbConnector clientDbConnector = new DbConnector();


    public Client(String client_id) {
        String request = String.format("SELECT * FROM client WHERE id=\"%s\";", client_id);
        ResultSet result = clientDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                setId(result.getString("id"));
                setFirstName(result.getString("firstName"));
                setLastName(result.getString("lastName"));
                setEmail(result.getString("email"));
                setBirthDate(String.valueOf(result.getDate("birthDate")));
                setPhoneNumber(result.getInt("phoneNumber"));
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
