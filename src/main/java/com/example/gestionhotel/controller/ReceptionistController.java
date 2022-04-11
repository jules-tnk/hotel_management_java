package com.example.gestionhotel.controller;

import com.example.gestionhotel.model.Client;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Worker;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.Date;

public class ReceptionistController {
    {
        new DbConnector();
    }
    public TextField firstNameSearchEntry;
    public TextField lastNameSearchEntry;
    public TextField emailSearchEntry;
    public TextField idClientSearchEntry;
    static Worker receptionist;

    //ADD
    @FXML private TextField firstNameEntry;
    @FXML private TextField lastNameEntry;
    @FXML private TextField phoneEntry;
    @FXML private TextField emailEntry;
    @FXML private TextField birthDateEntry;
    @FXML private TextField idClientEntry;

    //REMOVE
    @FXML private TextField idClientToRemoveEntry;

    //UPDATE
    @FXML private TextField firstNameUpdateEntry;
    @FXML private TextField lastNameUpdateEntry;
    @FXML private TextField phoneUpdateEntry;
    @FXML private TextField emailUpdateEntry;
    @FXML private TextField birthDateUpdateEntry;
    @FXML private TextField idClientUpdateEntry;

    //SEARCH

    //GETTER
    public Worker getReceptionist() { return receptionist; }

    //SETTER
    public static void setReceptionist(Worker newReceptionist) { receptionist = newReceptionist; }

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
        //receptionist.addClient(newClient);
        DbConnector.addClient(newClient);
    }

    @FXML
    public void removeClient(){
        String idClientToRemove = idClientToRemoveEntry.getText();
        //receptionist.removeClient(idClientToRemove);
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
}
