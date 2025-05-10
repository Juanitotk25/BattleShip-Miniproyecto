module com.example.miniproyecto3_battleship {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires jdk.xml.dom;


    opens com.example.miniproyecto3_battleship to javafx.fxml;
    exports com.example.miniproyecto3_battleship;
    exports com.example.miniproyecto3_battleship.controller;
    opens com.example.miniproyecto3_battleship.controller to javafx.fxml;
}