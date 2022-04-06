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
    DbConnector loginDbConnector = new DbConnector();

    public void Login(String username, String password){
        String savedPassword = "";
        String savedFunction = "";

        //Send request to database
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\";",username);
        ResultSet result = loginDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                //retrieve information from the request's results
                savedPassword = result.getString("password");
                savedFunction = result.getString("function");
            }
        } catch (SQLException e){
            //show login error
            System.out.println("User not found...");
            e.printStackTrace();
        }

        //Check password then the user function
        if (password.equals(savedPassword)){
            if (savedFunction.equals("admin")){
                //open admin view
                System.out.println("Admin logged in...");
            }
            else if (savedFunction.equals("receptionist")) {
                //open receptionist view
                System.out.println("Receptionist logged in...");
            }
        }
        else {//password not matching
            System.out.println("Wrong password...");
        }

    }

    public void Login2(String username, String password){
        String savedPassword = "";

        //Retrieve password from database
        String request = String.format("SELECT * FROM worker WHERE id=\"%s\";",username);
        ResultSet result = loginDbConnector.executeRequest(request);
        try {
            while (result.next()) {
                savedPassword = result.getString("password");
            }
        } catch (SQLException e){ e.printStackTrace(); }

        //Check password
        if (savedPassword.equals(password)){
            //Create the worker object corresponding to the id
            // and get his function
            Worker workerLoggingIn = new Worker(username);
            String workerFunction = workerLoggingIn.getFunction();

            //Open the view corresponding to the user's function
            if (workerFunction.equals("admin")){
                //open admin view
                System.out.println("Admin logged in...");
            }
            else if (workerFunction.equals("receptionist")) {
                //open receptionist view
                System.out.println("Receptionist logged in...");
            }
        }
        else {//password not matching
            System.out.println("Wrong password...");
        }

    }

    public void onLoginButtonClick(){
        String username = ""; //get username textField
        String password = ""; //get password textField
        Login(username, password);
    }

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
