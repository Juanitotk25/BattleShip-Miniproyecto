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

public class GameSelectionStage extends Stage {

    public GameSelectionStage() throws IOException {
        // Cargar el FXML de la pantalla de selección de juego
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto3_battleship/gameSelectionView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // No permitir que la ventana se redimensione
        setResizable(false);
        setTitle("Battleship - Selección de Juego");

        // Icono de la ventana
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Image/favicon.png")).toExternalForm());
        getIcons().add(icon);

        // Obtener la resolución de la pantalla
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

        // Establecer el tamaño de la ventana al 100% de la pantalla
        setWidth(screenWidth);
        setHeight(screenHeight);

        // Establecer la escena
        setScene(scene);
        setMaximized(true); // Maximizar la ventana para que ocupe toda la pantalla

        // Mostrar la ventana
        show();
    }

    private static class GameStageHolder {
        private static GameSelectionStage INSTANCE;
    }

    // Método para obtener una instancia única de GameSelectionStage
    public static GameSelectionStage getInstance() throws IOException {
        GameSelectionStage.GameStageHolder.INSTANCE =
                GameSelectionStage.GameStageHolder.INSTANCE != null ?
                        GameSelectionStage.GameStageHolder.INSTANCE : new GameSelectionStage();
        return GameSelectionStage.GameStageHolder.INSTANCE;
    }


    // Método para cerrar la instancia de GameSelectionStage
    public static void deleteInstance() {
        GameSelectionStage.GameStageHolder.INSTANCE.close();
        GameSelectionStage.GameStageHolder.INSTANCE = null;
    }
}
