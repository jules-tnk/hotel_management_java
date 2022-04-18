package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import com.example.gestionhotel.model.Room;
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
import java.util.Objects;

public class ReceptionistRoomController {
    public TableView<Room> tableRoom;
    public TableColumn <Room, String> columnRoomId;
    public TableColumn <Room, String> columnRoomType;
    public TableColumn <Room, Double> columnRoomPrice;


    {
        new DbConnector();
    }
    @FXML private static Receptionist receptionist = new Receptionist();
    @FXML private TextField roomIdModifyEntry;
    @FXML private Label labelRoomAvailability;
    @FXML private ComboBox availabilityChoiceBox;
    @FXML private Label labelRoomModifyInfo;
    @FXML private Stage stage;
    @FXML private Scene scene;

    @FXML private ComboBox boxMinPrice;
    @FXML private ComboBox boxMaxPrice;
    @FXML private ComboBox boxAvailability;
    @FXML private ComboBox boxType;

    //GETTER
    public Receptionist getReceptionist() { return receptionist; }

    //SETTER
    public static void setReceptionist(Receptionist newReceptionist) { receptionist = newReceptionist; }

    //UPDATING ROOM AVAILABILITY
    @FXML
    public void displayRoomAvailability(){
        String roomId = roomIdModifyEntry.getText();
        boolean isRoomRegistered = DbConnector.isRoomRegistered(roomId);
        if ( isRoomRegistered ){
            boolean isRoomAvailable = DbConnector.isRoomAvailable(roomId);
            if ( isRoomAvailable ) {
                labelRoomAvailability.setText("Available");
            }
            else {
                labelRoomAvailability.setText("Not available");
            }
        }
        else {
            labelRoomAvailability.setText("This room does not exist");
        }
    }

    @FXML
    public void setRoomAvailability(){
        String roomId = roomIdModifyEntry.getText();
        String newAvailability = (String) availabilityChoiceBox.getValue();
        boolean isAvailabilityUpdated = false;
         if ( newAvailability.equals("Available") ){
             isAvailabilityUpdated = receptionist.setRoomAvailability(roomId, true);
         }
         else if ( newAvailability.equals("Not available") ){
             isAvailabilityUpdated = receptionist.setRoomAvailability(roomId, false);
         }

         if ( isAvailabilityUpdated ){
             labelRoomModifyInfo.setText("Update successfully completed");
         }
         else {
             labelRoomModifyInfo.setText("Error while updating");
         }
    }

    //SEARCHING ROOMS BY TAGS
    @FXML
    public void searchRoom () {
        //Retrieve information from view inputs
        double minPrice = Double.parseDouble( (String) boxMinPrice.getValue() );
        double maxPrice = Double.parseDouble( (String) boxMaxPrice.getValue() );
        String type = (String) boxType.getValue();
        String stringAvailability = (String) boxAvailability.getValue();
        Boolean isAvailable = Objects.equals(stringAvailability, "Available");

        //get the room list
        ObservableList<Room> roomList = receptionist.getRoomsByTags(minPrice, maxPrice, type, isAvailable);

        //set values of the table's columns
        columnRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //display in the table
        tableRoom.setItems(roomList);
    }

    //SWITCHING TO OTHERS VIEWS
    @FXML
    public void switchToClientManagementView(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionistClientView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setTitle("Clients management");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToTransactionManagementView(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionistTransactionView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            //stage.setMaximized(true);
            stage.setTitle("Transactions management");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
