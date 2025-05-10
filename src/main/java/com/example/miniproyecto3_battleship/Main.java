package com.example.miniproyecto3_battleship;

import com.example.miniproyecto3_battleship.view.WelcomeStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        WelcomeStage.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }
}