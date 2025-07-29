package com.example.miniproyecto3_battleship.view;

import com.example.miniproyecto3_battleship.controller.GameController;
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
 * Represents the main game stage in the Battleship application.
 * This stage is implemented using the Singleton design pattern to ensure that only one instance exists at any time.
 *
 * <p>The {@code GameStage} initializes the game view and provides access to the game controller.</p>
 *
 * <p>Authors: Juan David Lopez</p>
 */
public class GameStage extends Stage {

    /**
     * The game controller associated with this stage.
     * This controller manages the logic and interactions for the game.
     */
    static GameController gameController = new GameController();

    /**
     * Initializes the main game stage.
     * <p>
     * Loads the FXML file, sets up the scene, and configures the stage with specific dimensions and style.
     * The {@code GameController} is also initialized by retrieving it from the FXML loader.
     * </p>
     *
     * @throws IOException if there is an issue loading the FXML resource.
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto3_battleship/GameView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        initStyle(StageStyle.UNDECORATED);
        gameController = loader.getController();
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Image/favicon.png")).toExternalForm());
        getIcons().add(icon);
        setWidth(screenWidth * 1);
        setHeight(screenHeight * 1);
        setTitle("BattleShip");
        setScene(scene);
        show();
    }

    /**
     * Holds the singleton instance of the {@code GameStage}.
     * This is a private static class to ensure lazy initialization and thread safety.
     */
    private static class GameStageHolder {
        private static GameStage INSTANCE;
    }


    /**
     * Returns the game controller associated with this stage.
     *
     * @return the {@code GameController} instance managing the game logic and interactions.
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Returns the singleton instance of the {@code GameStage}.
     * <p>If the instance does not exist, it is created. Otherwise, the existing instance is returned.</p>
     *
     * @return the singleton instance of {@code GameStage}.
     * @throws IOException if there is an issue creating the instance (e.g., loading the FXML resource).
     */
    public static GameStage getInstance() throws IOException {
        GameStage.GameStageHolder.INSTANCE =
                GameStage.GameStageHolder.INSTANCE != null ?
                        GameStage.GameStageHolder.INSTANCE : new GameStage();
        return GameStage.GameStageHolder.INSTANCE;
    }

    /**
     * Deletes the singleton instance of the {@code GameStage}.
     * <p>Closes the current stage and sets the instance reference to {@code null}.</p>
     */
    public static void deleteInstance() {
        GameStage.GameStageHolder.INSTANCE.close();
        GameStage.GameStageHolder.INSTANCE = null;
    }
}
