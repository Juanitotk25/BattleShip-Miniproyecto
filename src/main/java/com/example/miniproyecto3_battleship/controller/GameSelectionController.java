package com.example.miniproyecto3_battleship.controller;

//import com.example.miniproyecto3_battleship.model.ships.*;
import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.GameStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameSelectionController {

    /*@FXML
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

    private Color colorDefault = Color.TRANSPARENT;
    private Color colorhover = Color.rgb(0, 0, 0, 0.5);

    Fragata[] fragatas = new Fragata[4];
    Destructor[] destructores = new Destructor[3];
    Submarino[] submarinos = new Submarino[2];
    Portaaviones[] portaaviones = new Portaaviones[1];
    ArrayList<Ship> ships = new ArrayList<>();
    ArrayList<int[]> shipsPosition = new ArrayList<>();
    private Ship shipSelected;
    private final int[][] shipsSelectedGrid = new int[10][10];
    private final int[][] positionsHeadShips = new int[10][10];
    private boolean habitable;
    private int actualShadowRow;
    private int actualShadowCol;

    @FXML
    private HBox hBoxDestructores;
    @FXML
    private HBox hBoxFragatas;
    @FXML
    private AnchorPane anchorPaneLeft;
    @FXML
    private AnchorPane anchorPaneMiddle;
    @FXML
    private Label nameCharacter;
    @FXML
    private ImageView imgCharacter;
    @FXML
    private Button randomButton;

    private final Rectangle[][] shadowShipsSelection = new Rectangle[10][10];

    private Sounds mainMusic;
    private Sounds playclick;

    @FXML
    public void initialize() {
        mainMusic = new Sounds();
        mainMusic.loadSound("src/main/resources/com/example/miniproyecto_3_battlership/Sounds/gameSelectionSound.wav");
        mainMusic.loopSound();

        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto_3_battlership/Image/backgroundSelection.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        gameBorderPane.setBackground(new Background(background));

        createShadowShip();

        for (int i = 0; i < 4; i++) {
            fragatas[i] = new Fragata();
            int finalI = i;
            fragatas[i].setOnMouseClicked(e -> shipSelected(fragatas[finalI]));
            hBoxFragatas.getChildren().add(fragatas[i]);
            ships.add(fragatas[i]);
        }

        for (int i = 0; i < 3; i++) {
            destructores[i] = new Destructor();
            int finalI = i;
            destructores[i].setOnMouseClicked(e -> shipSelected(destructores[finalI]));
            hBoxDestructores.getChildren().add(destructores[i]);
            ships.add(destructores[i]);
        }

        for (int i = 0; i < 2; i++) {
            submarinos[i] = new Submarino();
            int finalI = i;
            submarinos[i].setOnMouseClicked(e -> shipSelected(submarinos[finalI]));
            hBoxSubmarinos.getChildren().add(submarinos[i]);
            ships.add(submarinos[i]);
        }

        for (int i = 0; i < 1; i++) {
            portaaviones[i] = new Portaaviones();
            int finalI = i;
            portaaviones[i].setOnMouseClicked(e -> shipSelected(portaaviones[finalI]));
            hBoxPortaAviones.getChildren().add(portaaviones[i]);
            ships.add(portaaviones[i]);
        }

        infoLabel.setText("Teniente seleccione sus barcos");
        setCharacter();
    }

    public void setCharacter() {
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        String nameCharacterActual = data[0];
        Image imageCharacterActual;
        nameCharacter.setText(nameCharacterActual);
        if (Objects.equals(nameCharacterActual, "Coronel Sander")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character1.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Almte. Zemansky")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character2.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Mayor Lovelace")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/character3.png")));
            imgCharacter.setImage(imageCharacterActual);
        }
        // Otros casos de personajes...
    }

    @FXML
    public void onHandleRandomButton() {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(randomButton);
        rotateTransition.setCycleCount(1);
        rotateTransition.setDuration(Duration.seconds(.5));

        Random rand = new Random();
        int randomRotation = rand.nextInt(360) + 360 * 3;

        rotateTransition.setByAngle(randomRotation);
        rotateTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(randomButton);
        translateTransition.setCycleCount(2);
        translateTransition.setDuration(Duration.seconds(0.1));

        translateTransition.setByX(10);
        translateTransition.setAutoReverse(true);

        translateTransition.play();
        rotateTransition.play();

        rotateTransition.setOnFinished(event2 -> {
            int randomRow, randomCol, randomHorientation;
            if (shipSelected != null) {
                shipSelected(shipSelected);
            }
            for (int i = 0; i < ships.size(); i++) {
                shipSelected(ships.get(i));
                do {
                    randomRow = (int) (Math.random() * 9);
                    randomCol = (int) (Math.random() * 9);
                    randomHorientation = (int) (Math.random() * 1);
                    if (randomHorientation == 0) {
                        onHandleBorderPaneKeyTyped2();
                    }
                    onHandleMouseEnteredShips(randomRow, randomCol);
                    onHandleMouseClickedShips(randomRow, randomCol);
                    onHandleMouseExitedShips(randomRow, randomCol);

                } while (!shipSelected.isPlaced() && !habitable);
                shipSelected(shipSelected);
            }
            resetShadow();
            infoLabel.setText("Teniente se pusieron sus barcos de manera estrategica");
        });
    }

    public void shipSelected(Ship ship) {
        if (shipSelected != null && shipSelected != ship) {
            shipSelected.originDesing();
            shipSelected = null;
        }
        if (ship.isSelect()) {
            ship.originDesing();
            shipSelected = null;
        } else {
            ship.selectDesing();
            shipSelected = ship;
        }
    }

    @FXML
    void onHandleBorderPaneKeyTyped(KeyEvent event) {
        if (event.getCharacter().equalsIgnoreCase("R") && shipSelected != null) {
            if (!shipSelected.isPlaced()) {
                shipSelected.rotateShip();
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            } else {
                shipSelected.setPotentialRotate(!shipSelected.potentialRotate());
            }
            if (actualShadowCol != -1) {
                for (int i = 0; i < shadowShipsSelection.length; i++) {
                    for (int j = 0; j < shadowShipsSelection[i].length; j++) {
                        shadowShipsSelection[i][j].setFill(Color.TRANSPARENT);
                    }
                }
                onHandleMouseEnteredShips(actualShadowRow, actualShadowCol);
            }
        }
    }

    // Método para generar las sombras de los barcos.
    @FXML
    void createShadowShip() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto_3_battlership/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                shadowShipsSelection[rows - 1][col - 1] = cell;
                int finalRows = rows - 1;
                int finalCol = col - 1;
                shadowShipsSelection[rows - 1][col - 1].setOnMouseEntered(e -> onHandleMouseEnteredShips(finalRows, finalCol));
                shadowShipsSelection[rows - 1][col - 1].setOnMouseExited(e -> onHandleMouseExitedShips(finalRows, finalCol));
                shadowShipsSelection[rows - 1][col - 1].setOnMouseClicked(e -> onHandleMouseClickedShips(finalRows, finalCol));
                gridPaneShips.add(shadowShipsSelection[rows - 1][col - 1], col, rows);
            }
        }
    } */
}

