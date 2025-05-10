package com.example.miniproyecto3_battleship.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Objects;

public class WelcomeController {

    @FXML
    private Button btnContinue;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnOptions;
    @FXML
    private Button btnCredits;
    @FXML
    private Button btnQuitGame;

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private ImageView img4;
    @FXML
    private ImageView img5;

    @FXML
    private BorderPane welcomeBorderPane;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ImageView imgCharacter;

    private String nameCharacter;

    @FXML
    public void initialize() {
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

        // Set the characters in the ChoiceBox
        choiceBox.getItems().addAll(
                "Thrall", "Jaina Proudmoore", "Sylvanas Windrunner", "Anduin Wrynn", "Gul'dan", "Illidan Stormrage"
        );
        choiceBox.setValue("Thrall"); // Set a default value

        // Set up the action for each button
        btnContinue.setOnAction(this::onHandleContinueGame);
        btnNewGame.setOnAction(this::onHandlePlayGame);
        btnOptions.setOnAction(this::onHandleOptions);
        btnCredits.setOnAction(this::onHandleCredits);
        btnQuitGame.setOnAction(this::onHandleQuitGame);

        // Add a listener for the ChoiceBox selection change
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCharacter(newValue);  // Update the character image when a new character is selected
        });
    }

    // Method to update the character image based on the selected character
    private void selectionCharacter(String selectedCharacter) {
        Image characterImage = null;

        switch (selectedCharacter) {
            case "Thrall":
                characterImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character1.png")));
                break;
            case "Jaina Proudmoore":
                characterImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character2.png")));
                break;
            case "Sylvanas Windrunner":
                characterImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character3.png")));
                break;
            case "Anduin Wrynn":
                characterImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character4.png")));
                break;
        }

        if (characterImage != null) {
            imgCharacter.setImage(characterImage);  // Set the new image in the ImageView
        } else {
            System.out.println("Error: Image for " + selectedCharacter + " not found.");
        }
    }

    // Action for "Continuar" button
    @FXML
    private void onHandleContinueGame(ActionEvent event) {
        String selectedCharacter = choiceBox.getValue();
        System.out.println("Seleccionaste el personaje: " + selectedCharacter);
        // Logic to continue with the selected character
        // You can transition to the game scene or logic here
    }

    // Action for "Nuevo Juego" button
    @FXML
    private void onHandlePlayGame(ActionEvent event) {
        System.out.println("Nuevo juego iniciado!");
        // Logic for starting a new game
        // Reset game variables, clear settings, etc.
    }

    // Action for "Opciones" button
    @FXML
    private void onHandleOptions(ActionEvent event) {
        System.out.println("Abrir opciones...");
        // Logic for opening game options
        // Show options screen or dialog
    }

    // Action for "Créditos" button
    @FXML
    private void onHandleCredits(ActionEvent event) {
        System.out.println("Mostrar créditos...");
        // Logic for showing credits
        // Open a credits window or display information
    }

    // Action for "Salir del juego" button
    @FXML
    private void onHandleQuitGame(ActionEvent event) {
        System.out.println("Salir del juego...");
        // Logic to quit the game or close the window
        Stage stage = (Stage) btnQuitGame.getScene().getWindow();
        stage.close(); // Close the window
    }
}





