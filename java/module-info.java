module com.example.froggeroop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.froggeroop to javafx.fxml;
    exports com.example.froggeroop;
    exports com.example.froggeroop.controllers;
    opens com.example.froggeroop.controllers to javafx.fxml;
    exports com.example.froggeroop.util;
    opens com.example.froggeroop.util to javafx.fxml;
    exports com.example.froggeroop.models;
    opens com.example.froggeroop.models to javafx.fxml;
}