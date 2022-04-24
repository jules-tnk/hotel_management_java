package com.example.gestionhotel.controller.adminController;

import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

public class AdminReceptionistController {

    public TextField entryPassword;
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
    private TextField entryIdReceptionist;
    @FXML
    public Label addReceptionistLabelInfo;

    //FXML REMOVE TAB
    @FXML
    private TextField entryIdReceptionistToRemove;
    @FXML
    public Label labelRemoveReceptionist;

    public void addReceptionist(ActionEvent actionEvent) {
        String idReceptionist = entryIdReceptionist.getText();
        boolean isClientRegistered = DbConnector.isWorkerRegistered(idReceptionist);

        //check if client is registered
        if (isClientRegistered) {
            addReceptionistLabelInfo.setText("Receptionist already registered");
            return;
        }

        //retrieve information from the form fields
        String firstName = firstNameEntry.getText();
        String lastName = lastNameEntry.getText();
        String password = entryPassword.getText();
        int phoneNumber = Integer.parseInt(phoneEntry.getText());
        String email = emailEntry.getText();
        LocalDate localBirthDate = datePickerBirthDate.getValue(); //Get the date in the java local format
        Date birthDate = Date.valueOf(localBirthDate); //convert the date to the sql format

        //create the client object
        Receptionist newReceptionist = new Receptionist(firstName, lastName, idReceptionist, email, phoneNumber, birthDate, "receptionist", password);

        //add the client through the receptionist class
        boolean isReceptionistAdded = DbConnector.addReceptionist(newReceptionist);

        //check if the operation is completed successfully
        if (isReceptionistAdded) {
            addReceptionistLabelInfo.setText("Receptionist added successfully");
        } else {
            addReceptionistLabelInfo.setText("Error while adding the receptionist");
        }
    }

    public void removeReceptionist(ActionEvent actionEvent) {
        //retrieve the client id from the form field
        String idReceptionistToRemove = entryIdReceptionistToRemove.getText();
        boolean isReceptionistRegistered = DbConnector.isWorkerRegistered(idReceptionistToRemove);

        if (!isReceptionistRegistered) {
            labelRemoveReceptionist.setText("This receptionist is not registered");
            return;
        }

        //remove the client
        boolean isReceptionistRemoved = DbConnector.removeReceptionist(idReceptionistToRemove);

        //check if the operation is completed successfully
        if (isReceptionistRemoved) {
            labelRemoveReceptionist.setText("Receptionist removed successfully");
        } else {
            labelRemoveReceptionist.setText("Error while removing the receptionist");
        }
    }
}
