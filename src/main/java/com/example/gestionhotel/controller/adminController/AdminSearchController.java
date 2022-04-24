package com.example.gestionhotel.controller.adminController;

import com.example.gestionhotel.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class AdminSearchController {
    //FXML CLIENT TAB
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

    //FXML ROOM TAB
    @FXML public TableView<Room> tableRoom;
    @FXML public TableColumn <Room, String> columnRoomId;
    @FXML public TableColumn <Room, String> columnRoomType;
    @FXML public TableColumn <Room, Double> columnRoomPrice;
    @FXML private ComboBox boxMinPrice;
    @FXML private ComboBox boxMaxPrice;
    @FXML private ComboBox boxAvailability;
    @FXML private ComboBox boxType;

    //FXML TRANSACTION TAB
    @FXML private TextField entrySearchClientId;
    @FXML private TextField entrySearchRoomId;
    @FXML private DatePicker datePickerSearchStart;
    @FXML private DatePicker datePickerSearchEnd;
    @FXML private ComboBox boxSearchMinPrice;
    @FXML private ComboBox boxSearchMaxPrice;
    @FXML private TableColumn<Transaction, String> columnSearchClientId;
    @FXML private TableColumn<Transaction, String> columnSearchRoomId;
    @FXML private TableColumn<Transaction, String> columnSearchIdReceptionist;
    @FXML private TableColumn<Transaction, String> columnSearchDate;
    @FXML private TableColumn<Transaction, Double> columnSearchPrice;
    @FXML private TableColumn<Transaction, String> columnSearchNature;
    @FXML private TableColumn<Transaction, String> columnSearchIdTransaction;
    @FXML private TableView<Transaction> tableSearchTransaction;

    //CLIENT SEARCH
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

    public void displayClientsInTable(ObservableList<Client> clientList) {
        //set the values of the table's column
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

        //display in the table
        tableClient.setItems(clientList);
    }

    //ROOM SEARCH
    public void searchRoom () {
        //Retrieve information from view inputs
        double minPrice = Double.parseDouble( (String) boxMinPrice.getValue() );
        double maxPrice = Double.parseDouble( (String) boxMaxPrice.getValue() );
        String type = (String) boxType.getValue();
        String stringAvailability = (String) boxAvailability.getValue();
        Boolean isAvailable = Objects.equals(stringAvailability, "Available");

        //get the room list
        ObservableList<Room> roomList = DbConnector.getRoomsByTags(minPrice, maxPrice, type, isAvailable);

        //set values of the table's columns
        columnRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //display in the table
        tableRoom.setItems(roomList);
    }

    //SEARCH TRANSACTIONS WITH TAGS
    public void searchTransactionByClientId(ActionEvent event) {
        //retrieve info from the view fields
        String clientId = entrySearchClientId.getText();

        //get the transaction's list from the database
        ObservableList<Transaction> transactionList = DbConnector.getTransactionsByClientId(clientId);

        //display the list in the table
        displayTransactionsInTable(transactionList);
    }

    public void searchTransactionByRoomId(ActionEvent event) {
        //retrieve info from the view fields
        String roomId = entrySearchRoomId.getText();

        //get the transaction's list from the database
        ObservableList<Transaction> transactionList = DbConnector.getTransactionsByRoomId(roomId);

        //display the list in the table
        displayTransactionsInTable(transactionList);
    }

    public void searchTransactionByDate(ActionEvent event) {
        //Get the date in the java local format
        LocalDate localDateInf = datePickerSearchStart.getValue();
        LocalDate localDateSup = datePickerSearchEnd.getValue();

        //convert the date to the sql format
        Date dateInf = Date.valueOf(localDateInf);
        Date dateSup = Date.valueOf(localDateSup);

        //get the transaction's list
        ObservableList<Transaction> transactionList = DbConnector.getTransactionsByDate(dateInf, dateSup);

        //display the list in the table
        displayTransactionsInTable(transactionList);
    }

    public void searchTransactionByPrice(ActionEvent event) {
        //retrieve info from the view fields
        double minPrice = Double.parseDouble( (String) boxSearchMinPrice.getValue() );
        double maxPrice = Double.parseDouble( (String) boxSearchMaxPrice.getValue() );

        //get the transaction's list from the database
        ObservableList<Transaction> transactionList = DbConnector.getTransactionsByPrice(minPrice, maxPrice);

        //display the list in the table
        displayTransactionsInTable(transactionList);
    }

    public void searchTransactionByTags(ActionEvent event) {
        String clientId = entrySearchClientId.getText();
        String roomId = entrySearchRoomId.getText();
        double minPrice = Double.parseDouble( (String) boxSearchMinPrice.getValue() );
        double maxPrice = Double.parseDouble( (String) boxSearchMaxPrice.getValue() );

        //Get the date in the java local format
        LocalDate localDateInf = datePickerSearchStart.getValue();
        LocalDate localDateSup = datePickerSearchEnd.getValue();

        //convert the date to the sql format
        Date dateInf = Date.valueOf(localDateInf);
        Date dateSup = Date.valueOf(localDateSup);
    }

    public void displayTransactionsInTable(ObservableList<Transaction> transactionList){
        //set the values of the table's column
        columnSearchIdTransaction.setCellValueFactory(new PropertyValueFactory<>("idTransaction"));
        columnSearchClientId.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        columnSearchIdReceptionist.setCellValueFactory(new PropertyValueFactory<>("idReceptionist"));
        columnSearchRoomId.setCellValueFactory(new PropertyValueFactory<>("idRoom"));
        columnSearchNature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        columnSearchPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnSearchDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        //display the given list in the table
        tableSearchTransaction.setItems(transactionList);
    }
}
