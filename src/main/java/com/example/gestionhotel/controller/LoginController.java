package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.Admin;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public Label loginLabelInfo;
    public Button loginButton;
    {
        new DbConnector();
    }
    @FXML public TextField userNameEntry;
    @FXML public PasswordField passwordEntry;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login(ActionEvent event){
        String workerId = userNameEntry.getText();
        String password = passwordEntry.getText();
        boolean isWorkerRegistered = DbConnector.isWorkerRegistered(workerId);
        if ( isWorkerRegistered ){
            String savedPassword = DbConnector.getWorkerPassword(workerId);
            if ( password.equals(savedPassword) ){
                String workerFunction = DbConnector.getWorkerFunction(workerId);
                if ( workerFunction.equals("admin") ){
                    Admin adminLoggingIn = new Admin(workerId);
                    AdminController.setAdmin(adminLoggingIn);
                    //set admin to others views
                    switchToAdminView(event);
                }
                else if ( workerFunction.equals("receptionist") ){
                    Receptionist receptionistLoggingIn = new Receptionist(workerId);
                    ReceptionistClientController.setReceptionist(receptionistLoggingIn);
                    ReceptionistRoomController.setReceptionist(receptionistLoggingIn);
                    ReceptionistTransactionController.setReceptionist(receptionistLoggingIn);
                    switchToReceptionistClientView(event);
                }
            }
            else { loginLabelInfo.setText("Wrong password"); }
        }
        else { loginLabelInfo.setText("User not registered"); }
    }

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

}
