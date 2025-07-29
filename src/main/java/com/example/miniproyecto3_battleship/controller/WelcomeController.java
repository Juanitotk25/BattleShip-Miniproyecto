package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.GameStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the Welcome screen in the Battleship game.
 * This class manages the UI components and interactions for the welcome stage,
 * including buttons, choice boxes, background images, and sounds.
 *
 * <p>Authors: Juan David Lopez Vanegas - cod 2243077
 */

public class WelcomeController {
    @FXML
    private Button btnContinue;

    @FXML
    private ImageView img1;

    @FXML
    private Button btnCredits;

    @FXML
    private ImageView img4;

    @FXML
    private Button btnNewGame;

    @FXML
    private ImageView img2;

    @FXML
    private Button btnOptions;

    @FXML
    private ImageView img3;


    @FXML
    private Button btnQuitGame;

    @FXML
    private ImageView img5;

    @FXML
    private BorderPane welcomeBorderPane;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ImageView imgCharacter;

    private String nameCharacter;

    private PlainTextFileHandler plainTextFileHandler;

    /**
     * Initializes the WelcomeController.
     * <p>
     * This method sets up the background image, loads and plays the main music,
     * initializes the sounds for button interactions, and populates the choice box
     * with character options. It also applies hover styles to buttons and sets up
     * event listeners for user interactions.
     * </p>
     */

    @FXML
    public void initialize() {
        plainTextFileHandler = new PlainTextFileHandler();

        // Sets the background image for the welcome screen.
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto3_battleship/Image/background_game.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        welcomeBorderPane.setBackground(new Background(background));

        // Populates the choice box with character options and sets the default selection.
        choiceBox.getItems().addAll("Thrall", "Jaina Proudmoore", "Sylvanas Windrunner", "Anduin Wrynn", "Gul'dan", "Illidan Stormrage");
        selectionCharacter("Thrall");

        // Adds a listener to the choice box to play a sound and update the character selection on change.
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCharacter(newValue);
        });

        // Applies hover styles to buttons and checks for saved game files.
        btnHoverStyle(btnContinue, 1);
        btnHoverStyle(btnNewGame, 2);
        btnHoverStyle(btnOptions, 3);
        btnHoverStyle(btnCredits, 4);
        btnHoverStyle(btnQuitGame, 5);

        doesExist("game.ser");

    }

    /**
     * Updates the displayed character image and name based on the selected value.
     *
     * <p>Loads specific character images and assigns the appropriate image and name to the {@code imgCharacter} and {@code nameCharacter} properties
     * based on the input string.</p>
     *
     * @param newValue the name of the selected character, as chosen from the {@code ChoiceBox}.
     */

    private void selectionCharacter(String newValue) {

        Image character1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character1.png")));
        Image character2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character2.png")));
        Image character3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character3.png")));
        Image character4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character4.png")));
        Image character5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character5.png")));
        Image character6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character6.png")));

        if ( newValue == "Thrall") {
            imgCharacter.setImage(character1);
            nameCharacter = "Thrall";
        }else if (newValue == "Jaina Proudmoore") {
            imgCharacter.setImage(character2);
            nameCharacter = "Jaina Proudmoore";
        }else if (newValue == "Sylvanas Windrunner") {
            imgCharacter.setImage(character3);
            nameCharacter = "Sylvanas Windrunner";
        } else if (newValue == "Anduin Wrynn") {
            imgCharacter.setImage(character4);
            nameCharacter = "Anduin Wrynn";
        }else if (newValue == "Gul'dan") {
            imgCharacter.setImage(character5);
            nameCharacter = "Gul'dan";
        }
        else if (newValue == "Illidan Stormrage") {
            imgCharacter.setImage(character6);
            nameCharacter = "Illidan Stormrage";
        }
    }

    /**
     * Handles the action of starting a new game when the play button is clicked.
     * <p>
     * Stops the main music, saves the selected character name to a file, and transitions
     * from the Welcome Stage to the Game Selection Stage.
     * </p>
     *
     * @param actionEvent the event triggered by clicking the "Play" button.
     * @throws IOException if there is an issue transitioning to the {@code GameSelectionStage}.
     */

    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        plainTextFileHandler.writeToFile("character.txt", nameCharacter + "," + " " + "," + "0");
        WelcomeStage.deleteInstance();
        GameSelectionStage.getInstance();

    }

    /**
     * Checks if a file exists at the specified path and enables or disables the "Continue" button accordingly.
     *
     * <p>If the file exists, the "Continue" button is enabled, allowing the user to load a previous game.
     * Otherwise, the button is disabled.</p>
     *
     * @param path the path to the file to check.
     */

    public void doesExist(String path){
        File file = new File(path);
        if(file.exists()){
            btnContinue.setDisable(false);
        }else{
            btnContinue.setDisable(true);
        }
    }


    /**
     * Displays the credits dialog with the developers' names and associated actions.
     *
     * <p>The method initializes an alert of type {@code INFORMATION}</p>
     *
     * @param event the action event triggered when the "Credits" button is clicked.
     */
    @FXML
    public void onHandleCredits(ActionEvent event){
        System.out.println("Mostrar créditos...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Créditos");
        alert.setHeaderText("Desarrollado por:");
        alert.setContentText("Juan David Lopez Vanegas");
        alert.showAndWait();
    }

    /**
     * Continues a previously saved game.
     *
     * <p>Closes the current {@code WelcomeStage} and initializes the {@code GameStage}, invoking the
     * {@code Continue} method from the {@code GameController} to load the saved state.</p>
     *
     * @param event the action event triggered when the "Continue" button is clicked.
     * @throws IOException if there is an error while initializing the {@code GameStage}.
     */
    @FXML
    void onHandleContinueGame(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance().getGameController().Continue();
    }

    /**
     * Exits the application.
     *
     * <p>This method is triggered by the "Quit Game" button, terminating the program using {@code System.exit(0)}.</p>
     *
     * @param actionEvent the action event triggered when the "Quit Game" button is clicked.
     */

    @FXML
    public void onHandleQuitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }


    /**
     * Adds hover effects and sounds to buttons in the welcome screen.
     *
     * <p>When the mouse hovers over a button, a sound is played and the opacity of an associated image increases.
     * When the mouse leaves, the sound stops and the image opacity returns to its default state.</p>
     *
     * @param button the button to which the hover effect is applied.
     * @param i the index corresponding to the button, used to identify the associated image.
     */

    public void btnHoverStyle(Button button, int i) {
        button.setOnMouseEntered(mouseEvent -> {
            switch (i) {
                case 1:
                    img1.setOpacity(1);
                    break;
                case 2:
                    img2.setOpacity(1);
                    break;
                case 3:
                    img3.setOpacity(1);
                    break;
                case 4:
                    img4.setOpacity(1);
                    break;
                case 5:
                    img5.setOpacity(1);
                    break;
            }
        });
        button.setOnMouseExited(mouseEvent -> {
            switch (i) {
                case 1:
                    img1.setOpacity(0);
                    break;
                case 2:
                    img2.setOpacity(0);
                    break;
                case 3:
                    img3.setOpacity(0);
                    break;
                case 4:
                    img4.setOpacity(0);
                    break;
                case 5:
                    img5.setOpacity(0);
                    break;
            }
        });
    }
}





