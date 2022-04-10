package com.example.gestionhotel.controller;

import com.example.gestionhotel.model.Worker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class ReceptionistController extends Application {
    Worker receptionist;

    //GETTER
    public Worker getReceptionist() { return receptionist; }

    //SETTER
    public void setReceptionist(Worker receptionist) { this.receptionist = receptionist; }

    //CONSTRUCTOR
    public ReceptionistController(Worker receptionist) {
        setReceptionist(receptionist);
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(com.example.gestionhotel.Main.class.getResource("AdminView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setMaximized(true);
        stage.setTitle("Receptionist");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
