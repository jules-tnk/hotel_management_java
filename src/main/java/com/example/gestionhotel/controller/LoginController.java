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

public class LoginController extends Application {
    @FXML
    public void showAdminView() throws IOException {
        AdminController adminController = new AdminController();
        Stage adminStage = new Stage();
        adminController.start(adminStage);
    }

    @FXML
    public void showReceptionistView() throws IOException {
        ReceptionistController receptionistController = new ReceptionistController();
        Stage receptionnistStage = new Stage();
        receptionistController.start(receptionnistStage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.gestionhotel.Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        //stage.setMaximized(true);
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
