package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.model.Exeption.CrossedShipsException;
import com.example.miniproyecto3_battleship.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto3_battleship.model.ships.*;
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

/**
 * Controller class for the game selection screen in the Battleship game.
 *
 * <p>This class handles the initialization of the game selection UI and the interactions
 * related to selecting ships. It manages the visual representation of ships and their
 * placement on the game grid. The game selection includes background music, custom UI
 * elements, and ship selection functionality. The class is responsible for setting up
 * the game interface, handling ship selection, and updating relevant information for the
 * player.</p>
 *
 * @author Juan David Lopez Vanega - 2243077
 */
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


    private Color colorDefault = Color.TRANSPARENT;

    private Color colorhover = Color.rgb(0, 0, 0, 0.5);

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



    /**
     * Initializes the game selection screen for the Battleship game.
     *
     * <p>This method sets up the UI elements and functionality for selecting ships.
     * It performs the following tasks:
     * <ul>
     *     <li>Loads background music and applies it in a loop.</li>
     *     <li>Sets a custom background image for the {@code BorderPane}.</li>
     *     <li>Creates a grid shadow used to display ship placement.</li>
     *     <li>Initializes arrays of ships: {@code Fragata}, {@code Destructor}, {@code Submarino},
     *     and {@code Portaaviones}.</li>
     *     <li>Adds click event handlers to each ship, enabling selection of individual ships.</li>
     *     <li>Populates the corresponding {@code HBox} containers with ship objects.</li>
     *     <li>Updates the {@code infoLabel} with instructions for the player.</li>
     *     <li>Sets the character information (name and image) for the player based on previous selections.</li>
     * </ul>
     * </p>
     *
     * <p>It prepares the game interface, allowing the player to select ships and sets up the
     * background music, images, and the initial UI layout for ship selection.</p>
     *
     * @see Fragata
     * @see Destructor
     * @see Submarino
     * @see Portaaviones
     */
    public void initialize() {
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


    /**
     * Records the positions and statuses of all ships in the game.
     *
     * <p>This method iterates through the list of ships and retrieves their position, size, orientation,
     * and destruction status. These attributes are stored as arrays in the {@code shipsPosition} list
     * for future reference or validation during gameplay.</p>
     */

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

    /**
     * Sets the player's character and displays the corresponding name and image.
     *
     * <p>The method reads character data from a file (`character.txt`) using
     * {@code PlainTextFileHandler}. Based on the character's name, it updates the
     * {@code nameCharacter} label and sets the appropriate image in the {@code imgCharacter}
     * component. Additionally, specific styles and alignments are applied for certain characters.</p>
     */

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
        } else if (Objects.equals(nameCharacterActual, "Sylvanas Windrunner")) {
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character3.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Anduin Wrynn")) {
            nameCharacter.setStyle("-fx-font-size: 25; -fx-font-family: 'Berlin Sans FB'");
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character4.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Gul'dan")) {
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character5.png")));
            imgCharacter.setImage(imageCharacterActual);
        } else if (Objects.equals(nameCharacterActual, "Illidan Stormrage")) {
            nameCharacter.setAlignment(Pos.CENTER);
            imageCharacterActual = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character6.png")));
            imgCharacter.setImage(imageCharacterActual);
        }
    }

    /**
     * Randomly places all ships on the game board and provides visual feedback.
     *
     * <p>When the random placement button is clicked, this method performs the following tasks:
     * <ul>
     *     <li>Applies visual animations (rotation and translation) to the button.</li>
     *     <li>Randomly determines positions and orientations for each ship.</li>
     *     <li>Validates and places ships if the placement is valid and the area is habitable.</li>
     *     <li>Updates the information label to inform the player of the randomized placements.</li>
     * </ul>
     * </p>
     */

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

    /**
     * Manages the selection state and design of a ship.
     *
     * <p>This method updates the visual representation and selection status of a ship.
     * If another ship is already selected, it resets the design of the previously selected ship.
     * If the same ship is clicked again, it deselects it.</p>
     *
     * @param ship the {@code Ship} object being selected or deselected.
     */

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

    /**
     * Handles the "R" key press for rotating the currently selected ship.
     *
     * <p>This method checks if the "R" key is pressed and performs the following actions:
     * <ul>
     *     <li>Rotates the selected ship if it is not placed.</li>
     *     <li>Toggles the potential rotation state of the ship.</li>
     *     <li>Updates the visual "shadow" representation of the ship's potential position.</li>
     * </ul>
     * </p>
     *
     * @param event the {@code KeyEvent} triggered when a key is pressed.
     */

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

    /**
     * Internal helper method to handle ship rotation logic.
     *
     * <p>This method is similar to {@code onHandleBorderPaneKeyTyped} but does not require a key event.
     * It is used internally for automatic or programmatic rotation scenarios, such as random placement.</p>
     */

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

    /**
     * Creates and adds shadow ships to the grid for potential ship placement.
     *
     * <p>This method initializes a grid of transparent cells in the {@code gridPaneShips} and
     * assigns event handlers for mouse interactions on each cell. It is used for visualizing
     * potential ship placements and interactions.</p>
     *
     * <p>Each cell represents a potential position for a ship, and the event handlers
     * allow the player to interact with the cells to place ships or adjust their position.</p>
     */

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

    /**
     * Handles the mouse entering a grid cell during ship placement or rotation.
     *
     * <p>This method determines if the selected ship can be placed in the current grid cell and
     * highlights the cells where the ship can potentially be placed based on its size, orientation,
     * and the current grid status.</p>
     *
     * @param row the row index of the grid cell.
     * @param col the column index of the grid cell.
     */

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

    /**
     * Handles the event when the mouse exits a grid cell during ship placement.
     *
     * <p>This method resets the preview of the ship's potential placement by clearing the
     * highlighted cells when the mouse moves out of the grid cell. It reverts the cells
     * back to their default state.</p>
     *
     * @param row the row index of the grid cell.
     * @param col the column index of the grid cell.
     */

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

    /**
     * Handles the event when a grid cell is clicked for ship placement.
     *
     * <p>This method places a ship on the grid based on the selected position and orientation,
     * handling both horizontal and vertical placements. It also checks if the ship is placed,
     * removes any previously placed ship from the grid if needed, and adjusts the grid accordingly.</p>
     *
     * @param row the row index of the grid cell.
     * @param col the column index of the grid cell.
     */

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


    /**
     * Starts the game after verifying that all ships are placed on the grid.
     *
     * <p>This method checks if all ships are placed on the grid, with a total sum of 20 units
     * representing the ships' occupied cells. If the condition is met, it initiates the game,
     * including stopping background music, hiding UI elements, and starting the loading animation.</p>
     *
     * @param event the action event triggered when the start game button is clicked.
     * @throws IOException if an error occurs while loading the next scene or resources.
     */

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
            return;

        }
        rectangleLabelInfo.setOpacity(0);
        infoLabel.setOpacity(0);
        rectangleLabelSelection.setOpacity(0);
        lbSelecction.setOpacity(0);

        shipPositions();
        playVideoLoading();
        animation();
    }

    /**
     * Performs an animation that fades out and moves the left pane of the UI to the right.
     *
     * <p>This method applies a fade transition to the `anchorPaneLeft`, reducing its opacity from
     * fully visible to 50%. At the same time, a translation transition is applied to move the
     * pane horizontally to the right. Both transitions play simultaneously using a `ParallelTransition`.</p>
     */

    private void animation() {
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.8), anchorPaneLeft);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.5);

        TranslateTransition moveRight = new TranslateTransition(Duration.seconds(0.5), anchorPaneLeft);
        moveRight.setFromX(0);
        moveRight.setToX(anchorPaneLeft.getBoundsInParent().getWidth());

        ParallelTransition transitionLeft = new ParallelTransition(fadeOut, moveRight);
        transitionLeft.play();

    }


    /**
     * Plays a loading video during the transition between stages.
     *
     * <p>This method loads and plays a video from the specified path. The video is displayed
     * using a `MediaView` and spans the full screen size. After the video finishes playing,
     * it runs a method to set up the game grid and deletes the current game selection stage.</p>
     */

    private void playVideoLoading() {
        try {
            // Carga inmediatamente la vista de juego con posiciones de barcos
            GameStage.getInstance()
                    .getGameController()
                    .setGridPaneShips(shipsPosition, shipsSelected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Cierra la ventana de selección
        GameSelectionStage.deleteInstance();
    }

    /**
     * Resets the shadow preview of ship placements to the default state.
     *
     * <p>This method iterates over the `shadowShipsSelection` grid and sets the fill color
     * of each `Rectangle` back to its default transparent state. It is useful for clearing
     * the ship preview before the user makes a new selection or placement.</p>
     */

    public void resetShadow() {
        for (Rectangle[] rectangle : shadowShipsSelection) {
            for (Rectangle r : rectangle) {
                r.setFill(colorDefault);
            }
        }
    }

    /**
     * Handles the event when the player decides to return to the main menu.
     *
     * <p>This method stops any background music, deletes the current game selection stage,
     * and transitions back to the welcome stage of the application.</p>
     *
     * @param actionEvent the action event triggered when the return button is clicked.
     * @throws IOException if an error occurs during the stage transition.
     */

    @FXML
    public void onHandleReturn(ActionEvent actionEvent) throws IOException {
        GameSelectionStage.deleteInstance();
        WelcomeStage.getInstance();
    }

}




