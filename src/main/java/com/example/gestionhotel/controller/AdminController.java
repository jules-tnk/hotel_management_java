package com.example.gestionhotel.controller;

import com.example.gestionhotel.model.Worker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class AdminController {
    static Worker admin;

    //GETTER
    public Worker getAdmin() { return admin; }

    //SETTER
    public static void setAdmin(Worker newAdmin) { admin = newAdmin; }

}
