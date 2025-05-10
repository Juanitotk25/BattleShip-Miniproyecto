package com.example.miniproyecto3_battleship.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class GameStage extends Stage {

    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto3_battleship/gameStageView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // No permitir que la ventana se redimensione
        setResizable(false);
        setTitle("Battleship Game");

        // Icono de la ventana
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/favicon.png")).toExternalForm());
        getIcons().add(icon);

        // Obtener la resolución de la pantalla
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Establecer el tamaño al 100% de la pantalla
        setWidth(screenWidth);
        setHeight(screenHeight);

        // Configurar el estilo de la ventana
        initStyle(StageStyle.UNDECORATED); // Puedes eliminar esto si no quieres que sea sin bordes

        // Establecer la escena
        setScene(scene);
        setMaximized(true); // Maximiza la ventana para que ocupe toda la pantalla

        // Mostrar la ventana
        show();
    }

    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }

    public static GameStage getInstance() throws IOException {
        if (GameStageHolder.INSTANCE == null) {
            GameStageHolder.INSTANCE = new GameStage();
        }
        return GameStageHolder.INSTANCE;
    }

    public static void deleteInstance() {
        GameStageHolder.INSTANCE.close();
        GameStageHolder.INSTANCE = null;
    }
}
