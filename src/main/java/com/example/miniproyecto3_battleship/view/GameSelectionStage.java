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

/**
 * Represents the stage for selecting game options in the Battleship game.
 * This stage is implemented using the Singleton design pattern to ensure that only one instance exists at any time.
 *
 *
 * @author Juan David Lopez V
 */
public class GameSelectionStage extends Stage {

    /**
     * Initializes the game selection stage.
     * <p>
     * Loads the FXML file, sets up the scene, and configures the stage with specific dimensions and style.
     * The stage is displayed as undecorated and occupies the full screen.
     * </p>
     *
     * @throws IOException if there is an issue loading the FXML resource.
     */
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
        setWidth(screenWidth * 1);
        setHeight(screenHeight * 1);

        // Establecer la escena
        setScene(scene);
        //setMaximized(true); // Maximizar la ventana para que ocupe toda la pantalla

        // Mostrar la ventana
        show();
    }

    /**
     * Holds the singleton instance of the {@code GameSelectionStage}.
     * This is a private static class to ensure lazy initialization and thread safety.
     */
    private static class GameStageHolder {
        private static GameSelectionStage INSTANCE;
    }

    /**
     * Returns the singleton instance of the {@code GameSelectionStage}.
     * <p>If the instance does not exist, it is created. Otherwise, the existing instance is returned.</p>
     *
     * @return the singleton instance of {@code GameSelectionStage}.
     * @throws IOException if there is an issue creating the instance (e.g., loading the FXML resource).
     */

    public static GameSelectionStage getInstance() throws IOException {
        GameSelectionStage.GameStageHolder.INSTANCE =
                GameSelectionStage.GameStageHolder.INSTANCE != null ?
                        GameSelectionStage.GameStageHolder.INSTANCE : new GameSelectionStage();
        return GameSelectionStage.GameStageHolder.INSTANCE;
    }


    /**
     * Deletes the singleton instance of the {@code GameSelectionStage}.
     * <p>Closes the current stage and sets the instance reference to {@code null}.</p>
     */
    public static void deleteInstance() {
        GameSelectionStage.GameStageHolder.INSTANCE.close();
        GameSelectionStage.GameStageHolder.INSTANCE = null;
    }
}
