package com.example.gestionhotel.controller;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Worker;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    public void login(){
        String username = ""; //Retrive username from form
        String password = ""; //Retrieve password from form
        Worker workerLoggingIn = new Worker();
        workerLoggingIn.login(username, password);

        if (workerLoggingIn.getFunction().equals("admin")){
            showAdminView(workerLoggingIn);
        }
        else if (workerLoggingIn.getFunction().equals("receptionist")){
            showReceptionistView(workerLoggingIn);
        }
    }

    @FXML
    public void showAdminView(Worker admin) {
    }

    @FXML
    public void showReceptionistView(Worker receptionist) {

    }

}
