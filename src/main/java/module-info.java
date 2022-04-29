module com.example.gestionhotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires mysql.connector.java;

    requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires org.kordamp.ikonli.javafx;
    requires validatorfx;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires kernel;
    requires layout;

    opens com.example.gestionhotel to javafx.fxml;
    exports com.example.gestionhotel;
    exports com.example.gestionhotel.controller;
    opens com.example.gestionhotel.controller to javafx.fxml;
    exports com.example.gestionhotel.model;
    opens com.example.gestionhotel.model to javafx.fxml;
    exports com.example.gestionhotel.controller.adminController;
    opens com.example.gestionhotel.controller.adminController to javafx.fxml;
    exports com.example.gestionhotel.controller.receptionistController;
    opens com.example.gestionhotel.controller.receptionistController to javafx.fxml;
}