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
 * Represents the Welcome Stage of the Battleship game.
 * This class is implemented using the Singleton pattern to ensure that only one instance
 * of the stage is created and displayed. The stage is styled and configured
 * to provide the initial welcome interface of the application.
 * @author Juan David Lopez V
 */
public class WelcomeStage extends Stage {

    /**
     * Constructs a new WelcomeStage instance.
     * <p>This constructor initializes the stage with a predefined FXML layout,
     * applies a CSS stylesheet for styling, and configures the stage to be non-resizable
     * and undecorated. The stage's dimensions are set to match the primary screen size.</p>
     *
     * @throws IOException if there is an issue loading the FXML resource or stylesheet.
     */
    public WelcomeStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto3_battleship/welcomeView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Css/css.css")).toExternalForm());
        setTitle("BattleShip");
        initStyle(StageStyle.UNDECORATED);
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Image/favicon.png")).toExternalForm());
        getIcons().add(icon);
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        setWidth(screenWidth * 1);
        setHeight(screenHeight * 1);
        setScene(scene);
        show();
    }

    /**
     * Holds the singleton instance of the {@code WelcomeStage}.
     * This is a private static class to ensure lazy initialization and thread safety.
     */
    private static class WelcomeStageHolder {
        private static WelcomeStage INSTANCE;
    }

    /**
     * Retrieves the singleton instance of the WelcomeStage.
     * <p>If no instance exists, it initializes a new one. Otherwise, it returns the existing instance.</p>
     *
     * @return the singleton instance of {@code WelcomeStage}.
     * @throws IOException if there is an issue creating a new instance.
     */
    public static WelcomeStage getInstance() throws IOException {
        WelcomeStage.WelcomeStageHolder.INSTANCE =
                WelcomeStage.WelcomeStageHolder.INSTANCE != null ?
                        WelcomeStage.WelcomeStageHolder.INSTANCE : new WelcomeStage();
        return WelcomeStage.WelcomeStageHolder.INSTANCE;
    }

    /**
     * Deletes the current singleton instance of the WelcomeStage.
     * <p>This method closes the existing stage and sets the instance to null,
     * allowing a new instance to be created if needed.</p>
     */
    public static void deleteInstance() {
        WelcomeStage.WelcomeStageHolder.INSTANCE.close();
        WelcomeStage.WelcomeStageHolder.INSTANCE = null;
    }

}


