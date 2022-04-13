package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.Client;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceptionistController  implements Initializable {
    public static Receptionist receptionist;
    private Stage stage;
    private Scene scene;
    private Parent root;
    {
        new DbConnector();
    }

    //SEARCH
    @FXML private TextField firstNameSearchEntry;
    @FXML private TextField lastNameSearchEntry;
    @FXML private TextField emailSearchEntry;
    @FXML private TextField idClientSearchEntry;

    //ADD
    @FXML private TextField firstNameEntry;
    @FXML private TabPane MainPane;
    @FXML private TextField lastNameEntry;
    @FXML private TextField phoneEntry;
    @FXML private TextField emailEntry;
    @FXML private TextField birthDateEntry;
    @FXML private DatePicker birthDatePicker;
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
        LocalDate localBirthDate = birthDatePicker.getValue(); //Get the date in the java local format
        Date birthDate = Date.valueOf(localBirthDate); //convert the date to the sql format
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

    @FXML public void switchToRoomManagementView(ActionEvent event){
        System.out.println("Changing view...");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionistRoomView.fxml"));
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

    @FXML public void switchToTransactionManagementView(){}

    @FXML public void switchToClientManagement(ActionEvent event){
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
