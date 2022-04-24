package com.example.gestionhotel.controller.receptionistController;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
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
             isAvailabilityUpdated = DbConnector.setRoomAvailability(roomId, true);
         }
         else if ( newAvailability.equals("Not available") ){
             isAvailabilityUpdated = DbConnector.setRoomAvailability(roomId, false);
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
        ObservableList<Room> roomList = DbConnector.getRoomsByTags(minPrice, maxPrice, type, isAvailable);

        //set values of the table's columns
        columnRoomId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));

        //display in the table
        tableRoom.setItems(roomList);
    }

}
