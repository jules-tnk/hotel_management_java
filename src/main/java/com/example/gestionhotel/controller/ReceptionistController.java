package com.example.gestionhotel.controller;

import com.example.gestionhotel.model.Client;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ReceptionistController  implements Initializable {

    {
        new DbConnector();
    }
    public TextField firstNameSearchEntry;
    public TextField lastNameSearchEntry;
    public TextField emailSearchEntry;
    public TextField idClientSearchEntry;
    static Receptionist receptionist;

    //ADD
    @FXML private TextField firstNameEntry;
    @FXML private TabPane MainPane;
    @FXML private TextField lastNameEntry;
    @FXML private TextField phoneEntry;
    @FXML private TextField emailEntry;
    @FXML private TextField birthDateEntry;
    @FXML private TextField idClientEntry;
    @FXML public Label addClientLabelInfo;

    //REMOVE
    @FXML private TextField idClientToRemoveEntry;
    @FXML public Label removeClientLabelInfo;

    //UPDATE
    @FXML private TextField firstNameUpdateEntry;
    @FXML private TextField lastNameUpdateEntry;
    @FXML private TextField phoneUpdateEntry;
    @FXML private TextField emailUpdateEntry;
    @FXML private TextField birthDateUpdateEntry;
    @FXML private TextField idClientUpdateEntry;
    @FXML public Label modifyClientLabelInfo;

    //SEARCH

    //GETTER
    public Receptionist getReceptionist() { return receptionist; }

    //SETTER
    public static void setReceptionist(Receptionist newReceptionist) { receptionist = newReceptionist; }

    @FXML
    public void addClient(){
        String firstName = firstNameEntry.getText();
        String  lastName = lastNameEntry.getText();
        int phoneNumber = Integer.parseInt(phoneEntry.getText());
        String email = emailEntry.getText();
        String StringBirthDate = birthDateEntry.getText();
        Date birthDate = Date.valueOf(StringBirthDate);
        String idClient = idClientEntry.getText();
        Client newClient = new Client(firstName, lastName, idClient, email, phoneNumber, birthDate);
        //receptionist.checkIfClientExist();
        receptionist.addClient(newClient);
        //DbConnector.addClient(newClient);
        addClientLabelInfo.setText("Client added successfully");
        System.out.println(receptionist.getLastName());
    }

    @FXML
    public void removeClient(){
        String idClientToRemove = idClientToRemoveEntry.getText();
        boolean isClientRemoved = receptionist.removeClient(idClientToRemove);
        if ( isClientRemoved == true ){
            removeClientLabelInfo.setText("Client removed successfully");
        }
    }

    @FXML
    public void updateClient() {
        String firstName = firstNameUpdateEntry.getText();
        String  lastName = lastNameUpdateEntry.getText();
        int phoneNumber = Integer.parseInt(phoneUpdateEntry.getText());
        String email = emailUpdateEntry.getText();
        String StringBirthDate = birthDateUpdateEntry.getText();
        Date birthDate = Date.valueOf(StringBirthDate);
        String idClient = idClientUpdateEntry.getText();
        Client updatedClient = new Client(firstName, lastName, idClient, email, phoneNumber, birthDate);
        //receptionist.updateClient(updatedClient);
    }

    @FXML
    public void searchClient() {}

    public void testFunction(ActionEvent event) {
        System.out.println(ReceptionistController.receptionist.getLastName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
