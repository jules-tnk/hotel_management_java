package com.example.gestionhotel.controller.receptionistController;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import com.example.gestionhotel.model.Room;
import com.example.gestionhotel.model.Transaction;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class ReceptionistTransactionController {
    public static Receptionist receptionist = new Receptionist();

    //FXML SEARCH TAB
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

    //FXML RESERVE TAB
        //ROOM TABLE
    @FXML private TableColumn<Room, String> columnReserveRoomId;
    @FXML private TableColumn<Room, String> columnReserveRoomType;
    @FXML private TableColumn<Room, Double> columnReserveRoomPrice;
    @FXML private TableView<Room> tableReserveRoom;
    @FXML private ComboBox boxReserveType;
    @FXML private ComboBox boxReserveMaxPrice;
    @FXML private ComboBox boxReserveMinPrice;
    @FXML private TextField entryReserveClientId;
    @FXML private TextField entryReserveRoomId;
    @FXML private Label labelReserveTransaction;


    //FXML LIBERATION TAB
    @FXML private TextField entryLiberateRoomId;
    @FXML private CheckBox checkBoxLiberatePaid;
    @FXML private TextField entryLiberateClientId;
    @FXML private Label labelLiberateRoomId;
    @FXML private Label labelLiberateDayCount;
    @FXML private Label labelLiberateTotalPrice;
    @FXML private Label labelLiberateInfo;
    
    
    private Stage stage;
    private Scene scene;

    //GETTER
    public Receptionist getReceptionist() { return receptionist; }

    //SETTER
    public static void setReceptionist(Receptionist newReceptionist) { receptionist = newReceptionist; }

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

    //RESERVE A ROOM
    public void searchAvailableRoom(ActionEvent event) {
        //get the information from the view components
        double minPrice = Double.parseDouble( (String) boxReserveMinPrice.getValue() );
        double maxPrice = Double.parseDouble( (String) boxReserveMaxPrice.getValue() );
        String type = (String) boxReserveType.getValue();

        //get the available room list
        ObservableList<Room> roomList = receptionist.getRoomsByTags(minPrice, maxPrice, type,true);

        //set values of the table's columns
        columnReserveRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnReserveRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnReserveRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //display in the table
        tableReserveRoom.setItems(roomList);
    }

    public void addReservation(ActionEvent event) {
        //get the information from the view's components
        String clientId = entryReserveClientId.getText();
        String receptionistId = receptionist.getId();
        String roomId = entryReserveRoomId.getText();
        String nature = "Reservation";

        //check if the room is registered
        boolean isRoomRegistered = receptionist.isRoomRegistered(roomId);
        if ( !isRoomRegistered ){
            labelReserveTransaction.setText("This room does not exist");
            return;
        }

        //check if the room is available
        boolean isRoomAvailable = receptionist.isRoomAvailable(roomId);
        if (!isRoomAvailable){
            labelReserveTransaction.setText("This room is not available");
            return;
        }

        //check if the client is registered
        boolean isClientRegistered = receptionist.isClientRegistered(clientId);
        if ( !isClientRegistered ){
            labelReserveTransaction.setText("Client not registered");
            return;
        }

        //create a new transaction
        Transaction reservation = new Transaction(clientId, receptionistId, roomId, nature);

        //add the transaction
        //then check if the transaction is successfully completed
        boolean isTransactionAdded = receptionist.addReservation(reservation);

        if (isTransactionAdded){
            labelReserveTransaction.setText("Transaction complete successfully");
        }
        else {
            labelReserveTransaction.setText("Transaction failed");
        }
    }

    //LIBERATE A ROOM
    public void searchClientLastTransaction(ActionEvent event) {
        String clientId = entryLiberateClientId.getText();
        Transaction clientLastTransaction = receptionist.getClientLastTransaction(clientId);

        int numberOfDays = receptionist.getReservationDuration(clientLastTransaction.getIdTransaction());
        if (numberOfDays == 0 ) numberOfDays++;
        double roomPricePerDay = receptionist.getRoomPrice(clientLastTransaction.getIdRoom());
        double totalPrice = roomPricePerDay * (double)numberOfDays;

        //display information on the view
        labelLiberateRoomId.setText(clientLastTransaction.getIdRoom());
        labelLiberateDayCount.setText(String.valueOf(numberOfDays));
        labelLiberateTotalPrice.setText(String.valueOf(totalPrice));
    }

    public void addLiberation(ActionEvent event) {
        String roomId = entryLiberateRoomId.getText();

        //Check if the room is already free
        boolean isRoomAvailable = DbConnector.isRoomAvailable(roomId);
        if (isRoomAvailable){
            labelLiberateInfo.setText("Room already liberated");
            return;
        }

        //get information from the view components
        String clientId = entryLiberateClientId.getText();
        String receptionistId = receptionist.getId(); //get the id of the current receptionist
        String nature = "Liberation";
        double price = Double.parseDouble(labelLiberateTotalPrice.getText());

        //check if the client has paid
        boolean isRoomPaid = checkBoxLiberatePaid.isSelected();
        if (!isRoomPaid){
            //if the client has not paid, the price will be negative
            price = -1.0 * price;
        }

        //create a new transaction
        Transaction liberation = new Transaction(clientId, receptionistId, roomId, nature, price);

        //add the transaction
        //then check if the transaction is successfully completed
        boolean isTransactionAdded = receptionist.addLiberation(liberation);

        if (isTransactionAdded){
            labelLiberateInfo.setText("Transaction complete successfully");
        }
        else {
            labelLiberateInfo.setText("Transaction failed");
        }
    }

}
