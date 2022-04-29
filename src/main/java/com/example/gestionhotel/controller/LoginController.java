package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.controller.receptionistController.ReceptionistTransactionController;
import com.example.gestionhotel.model.Admin;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    //COMPONENTS OF THE VIEW
    @FXML private Label loginLabelInfo;

    @FXML private TextField userNameEntry;
    @FXML private PasswordField passwordEntry;

    private Stage stage;
    private Scene scene;

    @FXML
    public void login(ActionEvent event){
        //create a connection to the DB
        DbConnector.connectDatabase();

        //get the worker's information from the textFields
        String workerId = userNameEntry.getText();
        String password = passwordEntry.getText();

        //check if the worker is registered in the database
        boolean isWorkerRegistered = DbConnector.isWorkerRegistered(workerId);
        if ( !isWorkerRegistered ){
            loginLabelInfo.setText("Worker not registered");
            return;
        }

        //check if the password corresponds to the one stored in the database
        String savedPassword = DbConnector.getWorkerPassword(workerId);
        if ( !password.equals(savedPassword) ){
            loginLabelInfo.setText("Wrong password");
            return;
        }

        //get the worker from the database
        String workerFunction = DbConnector.getWorkerFunction(workerId);

        if ( workerFunction.equals("admin") ){
            Admin adminLoggingIn = new Admin(workerId);
            switchToAdminView(event);
        }
        else if ( workerFunction.equals("receptionist") ){
            Receptionist receptionistLoggingIn = new Receptionist(workerId);
            ReceptionistTransactionController.setReceptionist(receptionistLoggingIn);
            switchToReceptionistClientView(event);
        }
    }

    //SWITCH TO OTHER VIEW AFTER LOGIN
    @FXML
    public void switchToAdminView(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setTitle("Administrator");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToReceptionistClientView(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionistView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setTitle("Receptionist");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
