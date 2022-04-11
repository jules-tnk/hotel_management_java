package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    {
        new DbConnector();
    }
    public TextField userNameEntry;
    public PasswordField passwordEntry;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login(ActionEvent event){
        String username = userNameEntry.getText();
        String password = passwordEntry.getText();
        Worker workerLoggingIn = new Worker();
        boolean isWorkerLoggedIn = workerLoggingIn.login(username, password);
        System.out.println("try login...");
        if ( isWorkerLoggedIn ){
            System.out.println("logged in...");
            if (workerLoggingIn.getFunction().equals("admin")){
                showAdminView(workerLoggingIn, event);
            }
            else if (workerLoggingIn.getFunction().equals("receptionist")){
                showReceptionistClientView(workerLoggingIn, event);
            }
        }
        else{
            System.out.println("Wrong password...");
        }
    }

    @FXML
    public void showAdminView(Worker admin, ActionEvent event) {
        AdminController.setAdmin(admin);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setMaximized(true);
            stage.setTitle("Administrator");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showReceptionistClientView(Worker receptionist, ActionEvent event) {
        ReceptionistController.setReceptionist(receptionist);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("receptionistClientView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setMaximized(true);
            stage.setTitle("Clients management");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
