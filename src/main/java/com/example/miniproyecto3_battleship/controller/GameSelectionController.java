package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.GameStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import com.example.miniproyecto3_battleship.model.ships.*;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameSelectionController {

    @FXML
    private GridPane gridPaneShips;
    @FXML
    private BorderPane gameBorderPane;
    @FXML
    private VBox shipSelectVbox;
    @FXML
    private HBox hBoxSubmarinos;
    @FXML
    private HBox hBoxPortaAviones;
    @FXML
    private Label infoLabel;
    @FXML
    private Label lbSelecction;
    @FXML
    private Rectangle rectangleLabelInfo;
    @FXML
    private Rectangle rectangleLabelSelection;

    @FXML
    private Button startGameButton;
    @FXML
    private Button returnButton;
    @FXML
    private Button randomButton;  // Este es el botón "Random"

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<int[]> shipsPosition = new ArrayList<>();

    private Ship shipSelected;
    private final int[][] shipsSelected = new int[10][10];
    private final int[][] positionsHeadShips = new int[10][10];
    private boolean habitable;

    // Método de inicialización
    @FXML
    public void initialize() {
        // Establecer el fondo de la pantalla
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto3_battleship/Image/backgroundSelection.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        gameBorderPane.setBackground(new Background(background));

        // Asignar la funcionalidad a los botones
        //addButtonFunctionality();
    }

    // Asignar la lógica a los botones
    /*private void addButtonFunctionality() {
        // Botón para comenzar el juego
        startGameButton.setOnAction(this::onHandleStartGame);

        // Botón para regresar a la pantalla anterior (o menú principal)
        returnButton.setOnAction(this::onHandleReturn);

        // Botón para generar un random
        randomButton.setOnAction(this::onHandleRandomButton);  // Asegúrate de que este método exista
    }*/

    // Acción para "Flota Lista!"
    @FXML
    public void onHandleStartGame(ActionEvent event) {
        System.out.println("Flota lista. Iniciando juego...");

        // Aquí realizamos la transición a GameStage (la pantalla de juego)
        try {
            // Cerramos el GameSelectionStage
            GameSelectionStage.deleteInstance(); // Asegúrate de que este método esté implementado correctamente
            GameStage.getInstance(); // Aquí deberías cargar la pantalla del juego (GameStage)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Acción para "Abandonar flota"
    @FXML
    public void onHandleReturn(ActionEvent event) {
        System.out.println("Abandonando la flota... Regresando a la pantalla principal.");

        // Aquí cerramos el GameSelectionStage y volvemos al WelcomeStage (pantalla principal)
        try {
            GameSelectionStage.deleteInstance();  // Cerramos GameSelectionStage
            WelcomeStage.getInstance();  // Regresamos al WelcomeStage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Acción para "Generar random"
    @FXML
    public void onHandleRandomButton(ActionEvent event) {
        System.out.println("Generando un random...");

        // Implementa la lógica de random aquí
        // Ejemplo de lógica que podrías agregar
        int randomValue = (int)(Math.random() * 100);  // Solo como ejemplo
        infoLabel.setText("Random Value: " + randomValue);

        // Puedes agregar otras animaciones o efectos si es necesario
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(randomButton);
        rotateTransition.setCycleCount(1);
        rotateTransition.setDuration(Duration.seconds(0.5));
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }

    // Método vacío para que no lance errores de compilación
    @FXML
    public void onHandleBorderPaneKeyTyped() {
        // Este es un método vacío que se requiere para evitar errores de compilación
    }
}




