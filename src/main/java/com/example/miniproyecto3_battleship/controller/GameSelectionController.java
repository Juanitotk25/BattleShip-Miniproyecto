package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.GameStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import com.example.miniproyecto3_battleship.model.ships.*;
import com.example.miniproyecto3_battleship.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto3_battleship.model.Exeption.CrossedShipsException;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

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

    private int actualShadowRow;

    private int actualShadowCol;

    private Color colorhover = Color.rgb(0, 0, 0, 0.5);

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

        infoLabel.setText("Teniente seleccione sus barcos");
        setCharacter();
    }

    public void setCharacter() {
        PlainTextFileHandler plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        String nameCharacterActual = data[0];
        Image imageCharacterActual;
        nameCharacter.setText(nameCharacterActual);
        if (Objects.equals(nameCharacterActual, "Thrall")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character1.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Jaina Proudmoore")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character2.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Sylvanas WindRunner")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character3.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Anduin Wrynn")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character4.png")));
            imgCharacter.setImage(imageCharacterActual);
        }
    }

    //guarda las posiciones y el status de los barcos en la estructura de datos llamada arraylist
    public void shipPositions() {
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            int row = ship.getPosition()[0];
            int col = ship.getPosition()[1];
            int size = ship.getSize();
            int horizontal = ship.isHorizontal() ? 1 : 0;
            int isDestroyed = ship.isDestroyed() ? 1 : 0;
            shipsPosition.add(new int[]{row, col, size, horizontal, isDestroyed});

        }
    }

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


    private void onHandleMouseEnteredShips(int row, int col) {
        actualShadowCol = col;
        actualShadowRow = row;
        colorhover = Color.rgb(0, 0, 0, 0.5);
        if (shipSelected != null) {
            try {
                habitable = true;
                System.out.println(shipSelected.isHorizontal());
                for (int j = 0; j < shipSelected.getSize(); j++) {
                    if (shipSelected.potentialRotate()) {
                        if (shipsSelected[row][col - j] != 0) {
                            throw new CrossedShipsException();
                        }
                    } else {
                        if (shipsSelected[row - j][col] != 0) {
                            throw new CrossedShipsException();
                        }
                    }
                }
                if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                    }
                } else {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row - (i - 1)][col].setFill(colorhover);
                    }

                }
            } catch (ArrayIndexOutOfBoundsException | CrossedShipsException x) {
                System.out.println("Error en la grilla");
                colorhover = Color.rgb(254, 0, 0, 0.2);
                try {
                    habitable = false;
                    if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row][col - (i - 1)].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    } else {
                        for (int i = 1; i <= shipSelected.getSize(); i++) {
                            shadowShipsSelection[row - (i - 1)][col].setFill(colorhover);
                            System.out.println("se pinto una vez");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error en la grilla 2");
                }

            }
        }
    }


    void onHandleBorderPaneKeyTyped2() {
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
    }

}




