package com.example.gestionhotel.controller.receptionistController;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.Client;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ReceptionistClientController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    //FXML SEARCH TAB
    @FXML
    private TextField firstNameSearchEntry;
    @FXML
    private TextField lastNameSearchEntry;
    @FXML
    private TextField emailSearchEntry;
    @FXML
    private TextField idClientSearchEntry;
    @FXML
    private TableView<Client> tableClient;
    @FXML
    private TableColumn<Client, String> columnFirstName;
    @FXML
    private TableColumn<Client, String> columnLastName;
    @FXML
    private TableColumn<Client, Integer> columnPhoneNumber;
    @FXML
    private TableColumn<Client, String> columnEmail;
    @FXML
    private TableColumn<Client, Date> columnBirthDate;


    //FXML ADD TAB
    @FXML
    private TextField firstNameEntry;

    @FXML
    private TextField lastNameEntry;
    @FXML
    private TextField phoneEntry;
    @FXML
    private TextField emailEntry;

    @FXML
    private DatePicker datePickerBirthDate;
    @FXML
    private TextField idClientEntry;
    @FXML
    public Label addClientLabelInfo;

    //FXML REMOVE TAB
    @FXML
    private TextField idClientToRemoveEntry;
    @FXML
    public Label labelRemoveClient;

    //FXML UPDATE TAB
    @FXML
    private TextField firstNameUpdateEntry;
    @FXML
    private TextField lastNameUpdateEntry;
    @FXML
    private TextField phoneUpdateEntry;
    @FXML
    private TextField emailUpdateEntry;
    @FXML
    private TextField birthDateUpdateEntry;
    @FXML
    private TextField idClientUpdateEntry;
    @FXML
    public Label modifyClientLabelInfo;


    //CLIENT MANAGEMENT
    @FXML
    public void addClient() {
        String idClient = idClientEntry.getText();
        boolean isClientRegistered = DbConnector.isClientRegistered(idClient);

        //check if client is registered
        if (isClientRegistered) {
            addClientLabelInfo.setText("Client already registered");
            return;
        }

        //retrieve information from the form fields
        String firstName = firstNameEntry.getText();
        String lastName = lastNameEntry.getText();
        int phoneNumber = Integer.parseInt(phoneEntry.getText());
        String email = emailEntry.getText();
        LocalDate localBirthDate = datePickerBirthDate.getValue(); //Get the date in the java local format
        Date birthDate = Date.valueOf(localBirthDate); //convert the date to the sql format

        //create the client object
        Client newClient = new Client(firstName, lastName, idClient, email, phoneNumber, birthDate);

        //add the client through the receptionist class
        boolean isClientAdded = DbConnector.addClient(newClient);

        //check if the operation is completed successfully
        if (isClientAdded) {
            addClientLabelInfo.setText("Client added successfully");
        } else {
            addClientLabelInfo.setText("Error while adding the client");
        }

    }

    @FXML
    public void removeClient() {
        //retrieve the client id from the form field
        String idClientToRemove = idClientToRemoveEntry.getText();
        boolean isClientRegistered = DbConnector.isClientRegistered(idClientToRemove);

        if (!isClientRegistered) {
            labelRemoveClient.setText("This client is not registered");
            return;
        }

        //remove the client
        boolean isClientRemoved = DbConnector.removeClient(idClientToRemove);

        //check if the operation is completed successfully
        if (isClientRemoved) {
            labelRemoveClient.setText("Client removed successfully");
        } else {
            labelRemoveClient.setText("Error while removing the client");
        }
    }

    @FXML
    public void updateClient() {
        String firstName = firstNameUpdateEntry.getText();
        String lastName = lastNameUpdateEntry.getText();
        int phoneNumber = Integer.parseInt(phoneUpdateEntry.getText());
        String email = emailUpdateEntry.getText();
        String StringBirthDate = birthDateUpdateEntry.getText();
        Date birthDate = Date.valueOf(StringBirthDate);
        String idClient = idClientUpdateEntry.getText();
        Client updatedClient = new Client(firstName, lastName, idClient, email, phoneNumber, birthDate);
        //receptionist.updateClient(updatedClient);
    }

    @FXML
    public void searchClientById(ActionEvent event) {
        String clientId = idClientSearchEntry.getText();

        //get the client list
        ObservableList<Client> clientList = DbConnector.getClientsById(clientId);

        //display in the table
        displayClientsInTable(clientList);
    }

    @FXML
    public void searchClientByFirstName(ActionEvent event) {
        String firstName = firstNameSearchEntry.getText();

        //get the client list
        ObservableList<Client> clientList = DbConnector.getClientsByFirstName(firstName);

        //display in the table
        displayClientsInTable(clientList);
    }

    @FXML
    public void searchClientByLastName(ActionEvent event) {
        String lastName = lastNameSearchEntry.getText();

        //get the client list
        ObservableList<Client> clientList = DbConnector.getClientsByLastName(lastName);

        //display in the table
        displayClientsInTable(clientList);
    }

    @FXML
    public void searchClientByEmail(ActionEvent event) {
        String email = emailSearchEntry.getText();

        //get the client list
        ObservableList<Client> clientList = DbConnector.getClientsByEmail(email);

        //display in the table
        displayClientsInTable(clientList);
    }

    public void displayClientsInTable(ObservableList<Client> clientList){
        //set the values of the table's column
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        //display in the table
        tableClient.setItems(clientList);
    }

}
