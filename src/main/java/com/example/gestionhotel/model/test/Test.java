package com.example.gestionhotel.model.test;

import com.example.gestionhotel.model.DbConnector;
import com.example.gestionhotel.model.Receptionist;
import javafx.event.ActionEvent;

public class Test {
    public static void main(String[] args) {
        DbConnector.connectDatabase();
        System.out.println( DbConnector.getWorkerPassword("eb416362") );
        Receptionist wrk = new Receptionist();
        System.out.println(wrk.getFunction());
    }

    public void addClient(ActionEvent event) {
    }

    public void searchClientByLastName(ActionEvent event) {
    }
}
