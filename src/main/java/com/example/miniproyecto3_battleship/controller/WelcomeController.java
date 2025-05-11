package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
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
        // Configura la imagen de fondo para la pantalla de bienvenida
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto3_battleship/Image/background_game.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        welcomeBorderPane.setBackground(new Background(background));

        // Configura los personajes en el ChoiceBox
        choiceBox.getItems().addAll(
                "Thrall", "Jaina Proudmoore", "Sylvanas Windrunner", "Anduin Wrynn", "Gul'dan", "Illidan Stormrage"
        );
        choiceBox.setValue("Thrall"); // Establece un valor predeterminado

        // Configura las acciones para cada botón
        btnContinue.setOnAction(this::onHandleContinueGame);
        btnNewGame.setOnAction(this::onHandlePlayGame);
        btnOptions.setOnAction(this::onHandleOptions);
        btnCredits.setOnAction(this::onHandleCredits);
        btnQuitGame.setOnAction(this::onHandleQuitGame);

        // Agrega un listener al cambio de selección del ChoiceBox
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectionCharacter(newValue);  // Actualiza la imagen del personaje cuando se selecciona uno nuevo
        });
    }

    // Método para actualizar la imagen del personaje basado en la selección
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
            imgCharacter.setImage(characterImage);  // Establece la nueva imagen en el ImageView
        } else {
            System.out.println("Error: Imagen para " + selectedCharacter + " no encontrada.");
        }
    }

    // Acción para el botón "Continuar"
    @FXML
    private void onHandleContinueGame(ActionEvent event) {
        String selectedCharacter = choiceBox.getValue();
        System.out.println("Seleccionaste el personaje: " + selectedCharacter);
        // Aquí podrías agregar la lógica para continuar el juego con el personaje seleccionado
        // Por ejemplo, cargar el progreso guardado y continuar con la etapa del juego
        // GameStage.getInstance(); // Pasar a la siguiente pantalla de juego
    }

    // Acción para el botón "Nuevo Juego"
    @FXML
    private void onHandlePlayGame(ActionEvent event) {
        System.out.println("Nuevo juego iniciado!");
        // Aquí deberías inicializar el juego desde cero
        // Resetear variables del juego, limpiar configuraciones, etc.
        WelcomeStage.deleteInstance();  // Cierra la ventana de bienvenida

        // Asegúrate de que GameSelectionStage se cargue correctamente
        try {
            GameSelectionStage.getInstance();  // Llama a la selección de juego
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Acción para el botón "Opciones"
    @FXML
    private void onHandleOptions(ActionEvent event) {
        System.out.println("Abrir opciones...");
        // Aquí deberías agregar la lógica para abrir una pantalla de opciones
        // Por ejemplo, mostrar una ventana con opciones de configuración del juego (como sonido, dificultad, controles)
    }

    // Acción para el botón "Créditos"
    @FXML
    private void onHandleCredits(ActionEvent event) {
        System.out.println("Mostrar créditos...");
        // Aquí deberías mostrar una ventana con los créditos del juego
        // Puedes usar un `Alert` o crear una nueva ventana que muestre los nombres de los desarrolladores
        // Ejemplo de cómo podrías hacerlo:
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Créditos");
        alert.setHeaderText("Desarrollado por:");
        alert.setContentText("Juan David Lopez Vanegas");
        alert.showAndWait();
    }

    // Acción para el botón "Salir del juego"
    @FXML
    private void onHandleQuitGame(ActionEvent event) {
        System.out.println("Salir del juego...");
        // Lógica para salir del juego o cerrar la ventana
        Stage stage = (Stage) btnQuitGame.getScene().getWindow();
        stage.close();  // Cierra la ventana del juego
    }
}






