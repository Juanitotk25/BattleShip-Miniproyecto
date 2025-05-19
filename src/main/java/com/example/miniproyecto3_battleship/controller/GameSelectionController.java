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

import java.io.IOException;
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
    private Color colorDefault = Color.TRANSPARENT;

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
    void onHandleStartGame(ActionEvent event) throws IOException {

        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (shipsSelected[i][j] != 0) {
                    totalSum++;
                }
            }
        }

        if (totalSum != 20) {
            infoLabel.setText("Teniente debe seleccionar todos los barcos antes de poder ir a la batalla");
            System.out.println("Te faltan barcos por seleccionar");
            return;
        }
        rectangleLabelInfo.setOpacity(0);
        infoLabel.setOpacity(0);
        rectangleLabelSelection.setOpacity(0);
        lbSelecction.setOpacity(0);

        shipPositions();

        GameStage.getInstance().getGameController().setGridPaneShips(shipsPosition, shipsSelected);
        GameSelectionStage.deleteInstance();
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

    private void onHandleMouseExitedShips(int row, int col) {
        actualShadowCol = -1;
        actualShadowRow = -1;
        if (shipSelected != null) {
            colorDefault = Color.TRANSPARENT;
            try {
                if ((shipSelected.isHorizontal() && !shipSelected.isPlaced()) || shipSelected.potentialRotate()) {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row][col - (i - 1)].setFill(colorDefault);
                    }
                } else {
                    for (int i = 1; i <= shipSelected.getSize(); i++) {
                        shadowShipsSelection[row - (i - 1)][col].setFill(colorDefault);
                    }
                }
            } catch (ArrayIndexOutOfBoundsException x) {

                System.out.println("Error en la grilla");

            }
        }

    }

    //Permite que maneje el evento de click cuando una celda de la grilla es clickeada para posicionamiento
    //coloca el barco de acuerdo a su orientación y posición
    public void onHandleMouseClickedShips(int row, int col) {
        row += 1;
        col += 1;

        if (shipSelected != null && habitable) {
            if (shipSelected.isPlaced()) {
                for (int i = 0; i < shipSelected.getSize(); i++) {
                    if (shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 0;
                    } else {
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 0;
                    }
                }
            }
            shipSelected.setIsPlaced(true);
            gridPaneShips.getChildren().remove(shipSelected);
            if (shipSelected.potentialRotate() != shipSelected.isHorizontal()) {
                shipSelected.rotateShip();
            }
            try {
                if (shipSelected.isHorizontal()) {
                    gridPaneShips.add(shipSelected, col - shipSelected.getSize() + 1, row);
                    GridPane.setRowSpan(shipSelected, 0);
                    GridPane.setColumnSpan(shipSelected, shipSelected.getSize());
                } else {
                    gridPaneShips.add(shipSelected, col, row - shipSelected.getSize() + 1);
                    GridPane.setColumnSpan(shipSelected, 0);
                    GridPane.setRowSpan(shipSelected, shipSelected.getSize());
                }
            } catch (IllegalArgumentException e) {
                shipSelected.setPosition(row - 1, col - 1);
                positionsHeadShips[row - 1][col - 1] = shipSelected.getSize();
                for (int i = 0; i < shipSelected.getSize(); i++) {
                    if (shipSelected.isHorizontal()) {
                        shipsSelected[shipSelected.getPosition()[0]][shipSelected.getPosition()[1] - i] = 1;
                    } else {
                        shipsSelected[shipSelected.getPosition()[0] - i][shipSelected.getPosition()[1]] = 1;
                    }

                }
                infoLabel.setText("Teniente seleccione sus barcos");
            }
        } else if (!habitable) {
            infoLabel.setText("Teniente ponga sus barcos en posiciones validas");
        }
    }

    // crea sombras a los barcos seleccionados para ver donde se ubicarán
    @FXML
    void createShadowShip() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Css/css.css")).toExternalForm());
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

    //este metodo devuelve las sombras en las grillas a su estado pro defecto
    public void resetShadow() {
        for (Rectangle[] rectangle : shadowShipsSelection) {
            for (Rectangle r : rectangle) {
                r.setFill(colorDefault);
            }
        }
    }

    //lógica del botón de random, hace que las posicioens de los barcos se organicen
    //de forma aleatoria, es decir, estrategicamente
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

}




