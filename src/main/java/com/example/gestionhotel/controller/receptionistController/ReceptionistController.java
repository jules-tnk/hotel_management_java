package com.example.gestionhotel.controller.receptionistController;

import com.example.gestionhotel.Main;
import com.example.gestionhotel.model.DbConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReceptionistController {
    private Stage stage;
    private Scene scene;

    public void logout(ActionEvent event) {
        //close connection to database
        DbConnector.disconnectDatabase();
        //change view
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader.load());
            stage.setMaximized(true);
            stage.setTitle("LOGIN");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
