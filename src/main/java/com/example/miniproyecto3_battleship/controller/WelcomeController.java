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
 * <p>Authors: Nicolás Córdoba, Samuel Arenas, Juan Manuel Ampudia</p>
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

    @FXML
    public void onHandlePlayGame(javafx.event.ActionEvent actionEvent) throws IOException {
        plainTextFileHandler.writeToFile("character.txt", nameCharacter + "," + " " + "," + "0");
        WelcomeStage.deleteInstance();
        GameSelectionStage.getInstance();

    }

    public void doesExist(String path){
        File file = new File(path);
        if(file.exists()){
            btnContinue.setDisable(false);
        }else{
            btnContinue.setDisable(true);
        }
    }


    @FXML
    public void onHandleCredits(ActionEvent event){
        System.out.println("Mostrar créditos...");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Créditos");
        alert.setHeaderText("Desarrollado por:");
        alert.setContentText("Juan David Lopez Vanegas");
        alert.showAndWait();
    }

    @FXML
    void onHandleContinueGame(ActionEvent event) throws IOException {
        WelcomeStage.deleteInstance();
        GameStage.getInstance().getGameController().Continue();
    }


    @FXML
    public void onHandleQuitGame(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }

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





