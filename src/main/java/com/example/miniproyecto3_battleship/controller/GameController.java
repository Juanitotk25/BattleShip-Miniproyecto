package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.model.Player.PlayerBot;
import com.example.miniproyecto3_battleship.model.Player.PlayerPerson;
import com.example.miniproyecto3_battleship.model.Game.Game;
import com.example.miniproyecto3_battleship.model.planeTextFile.PlainTextFileHandler;
import com.example.miniproyecto3_battleship.model.Serializable.Save;
import com.example.miniproyecto3_battleship.model.Serializable.SerializableFileHandler;
import com.example.miniproyecto3_battleship.model.ships.*;
import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.GameStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import static javafx.scene.paint.Color.ORANGE;

/**
 * Controller class for managing the game logic and interactions in the Battleship game.
 *
 * <p>This class is responsible for controlling the main game mechanics, including the
 * interaction between the player and the AI (PlayerBot), managing the game state, and
 * handling ship placements and attacks. It also handles loading and saving the game state
 * using serialization. The class manages the user interface for the game, including updating
 * the grid, displaying ships, and providing feedback to the player.</p>
 *
 * @author Juan David Lopez - 2243077
 */



public class GameController implements Serializable {

    private Game game;
    private PlayerBot playerBot;
    private PlayerPerson playerPerson;
    private final SerializableFileHandler serializableFileHandler = new SerializableFileHandler();



    @FXML
    private GridPane gridPaneGame;

    @FXML
    private GridPane gridPaneShips;

    @FXML
    private BorderPane gameBorderPane;

    @FXML
    private AnchorPane anchorPaneMiddle;

    @FXML
    private AnchorPane anchorPaneLeft;

    @FXML
    private Label nameCharacter;

    @FXML
    private Label lbNameVillain;

    @FXML
    private ImageView imgCharacter;

    @FXML
    private ImageView imgVillain;

    @FXML
    private Label infoLabel;

    @FXML
    private Button btnShowEnemyShips;

    private ArrayList<ArrayList<Integer>> matriz;
    private ArrayList<Ship> playerShips = new ArrayList<>();
    private ArrayList<Ship> auxPlayerShips;
    private ArrayList<Ship> enemyShips = new ArrayList<>();
    private ArrayList<Ship> auxEnemyShips;

    private final Rectangle[][] enemyShadow = new Rectangle[10][10];
    private Save save;

    private Image image;
    private ImagePattern imagePatter;
    private Image expls = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/explosion.png")));
    ImagePattern imagePattern = new ImagePattern(expls);

    private String nameCharacterActual;
    private String nameEnemyActual;
    private int imageEnemyActual;

    private int rowBot, columnbot, countShow;

    private PlainTextFileHandler plainTextFileHandler;


    /**
     * Initializes the game screen for the Battleship game.
     *
     * <p>This method sets up the UI elements and functionality for the main game screen.
     * It performs the following tasks:</p>
     * <ul>
     *     <li>Loads background music and applies it in a loop.</li>
     *     <li>Initializes the position for the enemy bot.</li>
     *     <li>Reads the character and enemy data from a text file for player and enemy names
     *     and the enemy's image.</li>
     *     <li>Sets up the background image for the game screen.</li>
     *     <li>Initializes the {@link ImagePattern} for the scope or target indicator.</li>
     * </ul>
     *
     * <p>The method ensures that all necessary resources for the game are loaded and that the
     * game interface is ready for player interaction.</p>
     *
     * @see PlainTextFileHandler
     * @see Save
     */


    public void initialize() {
        rowBot = 0;
        columnbot = 0;

        plainTextFileHandler = new PlainTextFileHandler();
        String[] data = plainTextFileHandler.readFromFile("character.txt");
        nameCharacterActual = data[0];
        nameEnemyActual = data[1];
        imageEnemyActual = Integer.parseInt(data[2]);

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/mira.png")));
        imagePatter = new ImagePattern(image);

        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto3_battleship/Image/background_game_battleship.png").toExternalForm());


        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );

        gameBorderPane.setBackground(new Background(background));
    }

    /**
     * Sets the grid and initializes the game with the given ship positions and selected ships.
     *
     * <p>This method sets up the game board by initializing the player and bot, placing the
     * ships on the grid, and saving the game state. It also creates the grid for the game
     * board and serializes the game and save data for future use.</p>
     *
     * <p>The method performs the following tasks:</p>
     * <ul>
     *     <li>Runs an animation for the game start.</li>
     *     <li>Initializes the {@link Game}, {@link PlayerBot}, and {@link PlayerPerson}.</li>
     *     <li>Sets up the matrix for the player and bot and sets the chosen ships for the player.</li>
     *     <li>Generates the bot's ships.</li>
     *     <li>Sets the character and enemy details for the game.</li>
     *     <li>Creates and displays the grid for ship placement.</li>
     *     <li>Serializes the current game and save data for persistence.</li>
     * </ul>
     *
     * @param shipsPositions A list of positions for the ships to be placed on the grid.
     * @param shipsSelected A 2D array representing the selected ship matrix for the player.
     */

    public void setGridPaneShips(ArrayList<int[]> shipsPositions, int[][] shipsSelected) {
        animationIn();

        game = new Game();
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        playerPerson.setMatrix();
        playerBot.setMatrix();
        playerPerson.setChosenMatrix(shipsSelected);
        playerBot.generateBotGame();

        setCharacter();
        setEnemy();

        save = new Save(shipsPositions);
        playerShips = save.getShip();
        auxPlayerShips = new ArrayList<>(playerShips);
        createGridPaneGame();

        gridPaneShips.setStyle("-fx-cursor: default;");

        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);

        playerPerson.showMatrix();
        System.out.println();
        playerBot.showMatrix();
        createEnemyShadows();
    }

    /**
     * Continues the game by loading the saved game and ship data from previous sessions.
     *
     * <p>This method restores the game state from previously serialized files, loading the
     * ships, players, and game settings. It reinitializes the grid, player, and enemy state
     * to continue from where the player left off.</p>
     *
     * <p>The method performs the following tasks:</p>
     * <ul>
     *     <li>Deserializes the saved game data from the "save.ser" and "game.ser" files.</li>
     *     <li>Restores the player and bot objects, along with the ship positions.</li>
     *     <li>Sets up the character and enemy details based on the saved data.</li>
     *     <li>Rebuilds the game grid and sets the appropriate styles and shadows.</li>
     *     <li>Loads the grid with the player's ships and initializes the game environment for the player.</li>
     * </ul>
     */


    public void Continue() {
        save = (Save) serializableFileHandler.deserialize("save.ser");
        game = (Game) serializableFileHandler.deserialize("game.ser");
        playerBot = game.getPlayerBot();
        playerPerson = game.getPlayerPerson();
        playerShips = save.getShip();
        auxPlayerShips = new ArrayList<>(playerShips);
        setCharacter();
        setEnemy();
        createGridPaneGame();
        gridPaneShips.setStyle("-fx-cursor: default;");
        createEnemyShadows();
        loadGridPaneShips();
        loadGridPaneGame();
    }

    /**
     * Creates the game grid for the ship placement and game board.
     *
     * <p>This method sets up the visual grid for the Battleship game, including the cells for
     * the ships to be placed and the background styles. It also adds the ships to the grid based
     * on their positions and orientations (horizontal/vertical).</p>
     *
     * <p>The method performs the following tasks:</p>
     * <ul>
     *     <li>Sets the grid's cell dimensions and styles using a custom CSS file.</li>
     *     <li>Creates a 10x10 grid for ship placement using {@link Rectangle} objects.</li>
     *     <li>Places each ship on the grid according to its position and orientation.</li>
     *     <li>Adjusts the grid spans for horizontal and vertical ships to ensure proper placement.</li>
     * </ul>
     *
     * @throws IllegalArgumentException If a ship cannot be placed on the grid due to incorrect positioning or size.
     */

    public void createGridPaneGame() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneShips.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Css/css.css")).toExternalForm());
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                gridPaneShips.add(cell, col, rows);
            }
        }

        for (int i = 0; i < playerShips.size(); i++) {
            Ship shipSelected = this.playerShips.get(i);
            int row = shipSelected.getPosition()[0] + 1;
            int col = shipSelected.getPosition()[1] + 1;
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
                System.out.println("Error: " + e.getMessage());
            }
        }

    }

    /**
     * Retrieves the positions and characteristics of the ships on the grid.
     *
     * <p>This method extracts the position (row and column), size, orientation (horizontal/vertical),
     * and destruction status of each ship in the provided list of ships. The data is returned as a list
     * of integer arrays, with each array containing these properties for a single ship.</p>
     *
     * @param ships The list of ships whose positions and characteristics are to be retrieved.
     * @return A list of integer arrays, each containing the following data for a ship:
     *         <ul>
     *             <li>Row position of the ship</li>
     *             <li>Column position of the ship</li>
     *             <li>Size of the ship</li>
     *             <li>Orientation (1 for horizontal, 0 for vertical)</li>
     *             <li>Destruction status (1 if destroyed, 0 if not)</li>
     *         </ul>
     */

    public ArrayList<int[]> shipPositions(ArrayList<Ship> ships) {
        ArrayList<int[]> shipInfo = new ArrayList<>();
        for (int i = 0; i < ships.size(); i++) {
            Ship ship = ships.get(i);
            int row = ship.getPosition()[0];
            int col = ship.getPosition()[1];
            int size = ship.getSize();
            int horizontal = ship.isHorizontal() ? 1 : 0;
            int isDestroyed = ship.isDestroyed() ? 1 : 0;
            shipInfo.add(new int[]{row, col, size, horizontal, isDestroyed});
        }
        return shipInfo;
    }

    /**
     * Toggles the visibility of the enemy ships on the grid.
     *
     * <p>This method iterates through the list of enemy ships and toggles their visibility status
     * if they are not destroyed. The visibility is switched between {@code true} and {@code false}
     * for each ship.</p>
     *
     * @see Ship#isDestroyed() for the check on whether the ship is destroyed before toggling visibility.
     */

    @FXML
    public void onHandlePutEnemyShips() {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (!(enemyShips.get(i).isDestroyed())) {
                enemyShips.get(i).setVisible(!enemyShips.get(i).isVisible());
            }
        }
    }

    /**
     * Sets the enemy's name and image for the current game session.
     *
     * <p>This method assigns a random enemy name and image if the enemy name is not already set.
     * It selects a name from a predefined list of enemy names and assigns one of two possible
     * images for the enemy character. The enemy's name and image are then displayed in the UI.</p>
     *
     * <p>If the enemy's name has already been set (i.e., it is not empty), the method simply
     * updates the enemy display with the stored name and image.</p>
     *
     * <p>The enemy's name and image are also saved in a text file for later retrieval.</p>
     *
     * @see PlainTextFileHandler#writeToFile(String, String) for saving the character data.
     */


    public void setEnemy() {
        if (Objects.equals(nameEnemyActual, " ")) {
            String[] enemys = {"Zarok", "Varek", "Drakk", "Korr", "Morth", "Tharn", "Vulkar", "Grim", "Raek", "Durn"};
            nameEnemyActual = enemys[(int) (Math.random() * 9)];
            lbNameVillain.setText(nameEnemyActual);
            imageEnemyActual = (int) (Math.random() * 2);
            plainTextFileHandler.writeToFile("character.txt", nameCharacterActual + "," + nameEnemyActual + "," + imageEnemyActual);
        } else {
            lbNameVillain.setText(nameEnemyActual);
        }
        if (imageEnemyActual == 0) {
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character5.png"))));
        } else {
            imgVillain.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/character6.png"))));
        }

    }

    /**
     * Sets the character's image and name in the game UI based on the current character's name.
     *
     * <p>This method checks the current character's name and updates the character's image and name
     * accordingly. The image is chosen from a set of predefined images based on the character's name,
     * and the font size and style are adjusted for certain characters.</p>
     *
     * <p>If the character's name is not recognized, the default image and alignment settings will be applied.</p>
     *
     * @see Image for loading and displaying character images.
     * @see Label#setText(String) for updating the character's name.
     */

    public void setCharacter() {

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
     * Handles the mouse click event for selecting a cell to attack in the enemy's grid.
     *
     * <p>This method updates the game state when a player clicks on a grid cell to attack the enemy.
     * If the attack hits an enemy ship, it updates the grid with a success symbol and triggers a player turn.
     * If the attack misses, it updates the grid with an error symbol and triggers the enemy bot's turn.</p>
     *
     * <p>The method also checks if a ship is destroyed after the attack and updates the game state accordingly.</p>
     *
     * @param row The row index of the clicked cell (1-based index).
     * @param column The column index of the clicked cell (1-based index).
     */

    public void onHandleMouseClickedShips(int row, int column) {
        row += 1;
        column += 1;

        matriz = playerBot.getMatrix();
        if (row != 0 && column != 0) {
            enemyShadow[row - 1][column - 1].setOnMouseClicked(null);
            enemyShadow[row - 1][column - 1].setOnMouseEntered(null);
            enemyShadow[row - 1][column - 1].setOnMouseExited(null);
            enemyShadow[row - 1][column - 1].setStyle("-fx-cursor: default;");
            if (matriz.get(row - 1).get(column - 1) != 0) {
                infoLabel.setText("Felicidad capitan le atinaste, tira nuevamente! ");
                gridPaneGame.add(successSymbol(), column, row);
                playerBot.changeMatrix(row - 1, column - 1, -1);
                PauseTransition pause = new PauseTransition(Duration.seconds(.5));
                pause.setOnFinished(event2 -> {
                    playerTurn();
                });
                pause.play();
                playerTurn();
                defeat((game.verifyWinner(playerBot)));
            } else {
                infoLabel.setText("Oh fallaste, deja a tu openete atacar");
                gridPaneGame.add(errorSymbol(), column, row);
                playerTurn();
                playerBot.changeMatrix(row - 1, column - 1, 2);
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event2 -> {
                    botAttack();
                });
                pause.play();
            }
        }
        DestroyedShip(row, column);

    }

    /**
     * Checks if a ship has been destroyed after an attack and updates the game state accordingly.
     *
     * <p>This method checks if the player has destroyed an enemy ship by verifying if all parts of the ship
     * have been hit. If a ship is destroyed, it marks the ship as destroyed, updates the grid to reflect the
     * destruction, and removes the ship from the enemy's fleet.</p>
     *
     * <p>If the ship is destroyed, a visual effect is added to show the destruction on the grid. The game state
     * is also saved and updated.</p>
     *
     * @param row The row index where the attack was made (1-based index).
     * @param column The column index where the attack was made (1-based index).
     */

    public void DestroyedShip(int row, int column){
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        playerBot.showMatrix();
        if (playerBot.getMatrix().get(row - 1).get(column - 1) == -1) {
            for (int i = 0; i < enemyShips.size(); i++) {
                isDestroyed = true;
                rowSelected = enemyShips.get(i).getPosition()[0];
                columnSelected = enemyShips.get(i).getPosition()[1];
                for (int j = 0; j < enemyShips.get(i).getSize(); j++) {
                    if (enemyShips.get(i).isHorizontal()) {
                        if (!(playerBot.getMatrix().get(rowSelected).get(columnSelected - j) == -1)) {
                            isDestroyed = false;
                        }
                    } else {
                        if (!(playerBot.getMatrix().get(rowSelected - j).get(columnSelected) == -1)) {
                            isDestroyed = false;
                        }
                    }
                }
                if (isDestroyed) {
                    enemyShips.get(i).setVisible(true);
                    enemyShips.get(i).setIsDestroyed(true);
                    infoLabel.setText("¡Has destruido un barco enemigo!");
                    for (int j = 0; j < enemyShips.get(i).getSize(); j++) {
                        if (enemyShips.get(i).isHorizontal()) {
                            gridPaneGame.add(destroyerFlame(), columnSelected + 1 - j, rowSelected + 1);
                        } else {
                            gridPaneGame.add(destroyerFlame(), columnSelected + 1, rowSelected + 1 - j);
                        }
                    }
                    enemyShips.remove(i);
                }
            }
        }
        save.setShipPositions(shipPositions(auxPlayerShips));
        playerBot.setEnemyShipsInfo(shipPositions(auxEnemyShips));
        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);
        victory(game.verifyWinner(playerBot));
    }

    public Group destroyerFlame(){
        Polygon flame = new Polygon();
        flame.getPoints().addAll(25.0, 0.0,   // Punta superior de la llama
                30.0, 8.0,   // Curva derecha superior
                35.0, 5.0,   // Pico derecho alto
                33.0, 15.0,  // Curva intermedia derecha
                40.0, 25.0,  // Pico derecho medio
                30.0, 28.0,  // Base derecha
                35.0, 40.0,  // Extremo inferior derecho
                25.0, 35.0,  // Centro inferior
                15.0, 40.0,  // Extremo inferior izquierdo
                20.0, 28.0,  // Base izquierda
                10.0, 25.0,  // Pico izquierdo medio
                17.0, 15.0,  // Curva intermedia izquierda
                15.0, 5.0,   // Pico izquierdo alto
                20.0, 8.0);
        flame.setScaleX(1.6);
        flame.setScaleY(1.6);
        flame.setRotate(180);
        flame.setFill(imagePattern); // Naranja rojizo
        flame.setStrokeWidth(0);
        Group group = new Group(flame);
        group.setEffect(new DropShadow(4, Color.BLACK));


        Image expls = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/explosion.png")));
        ImagePattern imagePattern = new ImagePattern(expls);
        flame.setFill(imagePattern);
        return group;
    }


    /**
     * Loads the player's ship grid into the game interface, displaying the current state of the ships.
     *
     * <p>This method iterates through the player's ship matrix, checking for specific values:
     * - A value of 2 indicates a missed attack and adds an error symbol to the grid.
     * - A value of -1 indicates a successful hit and adds a success symbol to the grid.</p>
     *
     * <p>Additionally, it calls {@link #loadDestroyedEnemyShip()} to check and update any destroyed enemy ships.</p>
     */


    public void loadGridPaneShips() {
        matriz = playerPerson.getMatrix();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz.get(i).get(j) == 2) {
                    gridPaneShips.add(errorSymbol(), j + 1, i + 1);
                } else if (matriz.get(i).get(j) == -1) {
                    gridPaneShips.add(successSymbol(), j + 1, i + 1);
                }
            }
        }
        loadDestroyedEnemyShip();
    }

    /**
     * Checks if any enemy ships have been destroyed and updates the grid to reflect the destruction.
     *
     * <p>This method iterates over all the player's ships, checking whether all parts of the ship have been hit.
     * If all parts of a ship are hit, the ship is marked as destroyed, and a visual effect is displayed on the grid
     * to indicate the destruction.</p>
     *
     * <p>The destroyed ship is also removed from the player's ship list, and a message is displayed indicating the destruction.</p>
     */

    public void loadDestroyedEnemyShip() {
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        for (int k = 0; k < playerShips.size(); k++) {
            isDestroyed = true;
            rowSelected = playerShips.get(k).getPosition()[0];
            columnSelected = playerShips.get(k).getPosition()[1];
            for (int l = 0; l < playerShips.get(k).getSize(); l++) {
                if (playerShips.get(k).isHorizontal()) {
                    if (!(matriz.get(rowSelected).get(columnSelected - l) == -1)) {
                        isDestroyed = false;
                    }
                } else {
                    if (!(matriz.get(rowSelected - l).get(columnSelected) == -1)) {
                        isDestroyed = false;
                    }
                }
            }
            if (isDestroyed) {
                playerShips.get(k).setVisible(true);
                playerShips.get(k).setIsDestroyed(true);
                infoLabel.setText("¡Has destruido un barco enemigo!");
                for (int j = 0; j < playerShips.get(k).getSize(); j++) {

                    if (playerShips.get(k).isHorizontal()) {
                        gridPaneShips.add(destroyerFlame(), columnSelected + 1 - j, rowSelected + 1);
                    } else {
                        gridPaneShips.add(destroyerFlame(), columnSelected + 1, rowSelected + 1 - j);
                    }
                }
                playerShips.remove(k);
            }
        }
    }

    /**
     * Loads the game grid for the player's bot, displaying the state of the enemy's ships.
     *
     * <p>This method checks the enemy's ship matrix for specific values:
     * - A value of 2 indicates a missed attack and adds an error symbol to the grid.
     * - A value of -1 indicates a successful hit and adds a success symbol to the grid.</p>
     *
     * <p>It also disables interaction with cells where ships have been hit or missed by removing mouse event listeners
     * and adding the corresponding visual symbols. Finally, it calls {@link #loadDestroyedShip()} to check and update
     * any destroyed ships.</p>
     */

    public void loadGridPaneGame() {
        matriz = playerBot.getMatrix();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matriz.get(i).get(j) == 2) {
                    gridPaneGame.add(errorSymbol(), j + 1, i + 1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                } else if (matriz.get(i).get(j) == -1) {
                    gridPaneGame.add(successSymbol(), j + 1, i + 1);
                    enemyShadow[i][j].setOnMouseClicked(null);
                    enemyShadow[i][j].setOnMouseEntered(null);
                    enemyShadow[i][j].setOnMouseExited(null);
                }
            }
        }
        loadDestroyedShip();
    }

    /**
     * Checks if any enemy ships have been destroyed and updates the grid to reflect the destruction.
     *
     * <p>This method iterates over all the enemy ships, checking whether all parts of each ship have been hit.
     * If all parts of a ship are hit, the ship is marked as destroyed, and a visual effect is displayed on the grid
     * to indicate the destruction. A message is also shown to inform the player that they have destroyed an enemy ship.</p>
     *
     * <p>After marking the ship as destroyed, the ship is removed from the list of enemy ships.</p>
     */


    public void loadDestroyedShip() {
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        for (int k = 0; k < enemyShips.size(); k++) {
            isDestroyed = true;
            rowSelected = enemyShips.get(k).getPosition()[0];
            columnSelected = enemyShips.get(k).getPosition()[1];
            for (int l = 0; l < enemyShips.get(k).getSize(); l++) {
                if (enemyShips.get(k).isHorizontal()) {
                    if (!(matriz.get(rowSelected).get(columnSelected - l) == -1)) {
                        isDestroyed = false;
                    }
                } else {
                    if (!(matriz.get(rowSelected - l).get(columnSelected) == -1)) {
                        isDestroyed = false;
                    }
                }
            }
            if (isDestroyed) {
                enemyShips.get(k).setVisible(true);
                enemyShips.get(k).setIsDestroyed(true);
                infoLabel.setText("¡Has destruido un barco enemigo!");
                for (int j = 0; j < enemyShips.get(k).getSize(); j++) {
                    if (enemyShips.get(k).isHorizontal()) {
                        gridPaneGame.add(destroyerFlame(), columnSelected + 1 - j, rowSelected + 1);
                    } else {
                        gridPaneGame.add(destroyerFlame(), columnSelected + 1, rowSelected + 1 - j);
                    }
                }
                enemyShips.remove(k);
            }
        }
    }

    /**
     * Toggles the player's turn by enabling or disabling the game grid.
     *
     * <p>This method is used to disable the player's interaction with the game grid after an action has been performed,
     * and then enables it again when it's the player's turn to act. This prevents the player from making multiple moves
     * during an opponent's turn.</p>
     */
    public void playerTurn() {
        gridPaneGame.setDisable(!gridPaneGame.isDisable());
    }

    /**
     * Simulates the bot's attack on the player's grid and updates the game state accordingly.
     *
     * <p>This method randomly generates the bot's next attack position and checks if the attack hits the player's ships.
     * - If the attack hits, it marks the position as a successful hit, updates the matrix, and checks if the player has been defeated.
     * - If the attack misses, it marks the position as a miss and allows the player to take their turn.</p>
     *
     * <p>The method uses a pause transition to create a delay between attacks, enhancing the gameplay experience by simulating thinking time.</p>
     *
     * @see #destroyedEnemyShip()
     */

    @FXML
    void botAttack() {
        infoLabel.setText("La maquina esta pensando...");
        playerBot.generatePositionRandom(playerPerson.getMatrix());
        rowBot = playerBot.getPositionRandom()[0];
        columnbot = playerBot.getPositionRandom()[1];
        matriz = playerPerson.getMatrix();
        System.out.print("row: " + rowBot + " column: " + columnbot);

        if (matriz.get(rowBot - 1).get(columnbot - 1) != 0) {
            infoLabel.setText("Tu opente ha acertado, atacara nuevamente");
            gridPaneShips.add(successSymbol(), columnbot, rowBot);
            playerPerson.changeMatrix(rowBot - 1, columnbot - 1, -1);
            defeat((game.verifyWinner(playerPerson)));
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event2 -> {
                infoLabel.setText("La maquina esta pensando...");
                botAttack();
            });
            pause.play();

        } else {
            infoLabel.setText("Tu oponente ha fallado, es tu turno");
            gridPaneShips.add(errorSymbol(), columnbot, rowBot);
            playerPerson.changeMatrix(rowBot - 1, columnbot - 1, 2);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> {
                playerTurn();
            });
            pause.play();
        }
        destroyedEnemyShip();

    }

    /**
     * Updates the game state after an enemy ship has been destroyed.
     *
     * <p>This method checks if any of the enemy's ships have been completely destroyed and adds visual effects on the game grid.
     * It also removes destroyed ships from the list of enemy ships.</p>
     *
     * @see #loadDestroyedShip()
     */

    public void destroyedEnemyShip() {
        int rowSelected;
        int columnSelected;
        boolean isDestroyed;
        if (playerPerson.getMatrix().get(rowBot - 1).get(columnbot - 1) == -1) {
            for (int i = 0; i < playerShips.size(); i++) {
                isDestroyed = true;
                rowSelected = playerShips.get(i).getPosition()[0];
                columnSelected = playerShips.get(i).getPosition()[1];
                for (int j = 0; j < playerShips.get(i).getSize(); j++) {
                    if (playerShips.get(i).isHorizontal()) {
                        if (!(playerPerson.getMatrix().get(rowSelected).get(columnSelected - j) == -1)) {
                            isDestroyed = false;
                        }
                    } else {
                        if (!(playerPerson.getMatrix().get(rowSelected - j).get(columnSelected) == -1)) {
                            isDestroyed = false;
                        }
                    }
                }
                if (isDestroyed) {
                    playerShips.get(i).setVisible(true);
                    playerShips.get(i).setIsDestroyed(true);
                    infoLabel.setText("¡El enemigo destruyo un barco!");
                    for (int j = 0; j < playerShips.get(i).getSize(); j++) {
                        if (playerShips.get(i).isHorizontal()) {
                            gridPaneShips.add(destroyerFlame(), columnSelected + 1 - j, rowSelected + 1);
                        } else {
                            gridPaneShips.add(destroyerFlame(), columnSelected + 1, rowSelected + 1 - j);
                        }
                    }
                    playerShips.remove(i);
                }
            }
        }
        save.setShipPositions(shipPositions(auxPlayerShips));
        playerBot.setEnemyShipsInfo(shipPositions(auxEnemyShips));
        serializableFileHandler.serialize("save.ser", save);
        serializableFileHandler.serialize("game.ser", game);
    }

    /**
     * Creates a symbol representing an error or missed attack in the game.
     *
     * <p>This method generates a red cross symbol to represent a missed attack. It creates two diagonal lines, each with a stroke width of 5 pixels,
     * and positions them in a way that they cross each other, forming an "X" shape. The resulting symbol is added to a `Group` container and returned.</p>
     *
     * @return A `Group` containing the red cross symbol representing an error or miss.
     */


    public Group errorSymbol() {
        Group group = new Group();
        Polygon xShape = new Polygon(
                // Coordenadas para la parte superior izquierda de la "X"
                10, 0,
                20, 0,
                30, 20,
                40, 0,
                50, 0,
                35, 30,
                50, 60,
                40, 60,
                30, 40,
                20, 60,
                10, 60,
                25, 30
        );

        // Estilo estético pirata
        xShape.setFill(Color.DARKRED); // Rojo oscuro que recuerda a un mapa antiguo
        xShape.setStroke(Color.BLACK); // Borde negro
        xShape.setStrokeWidth(2);

        // Efectos: Sombra
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(5);
        shadow.setOffsetX(3);
        shadow.setOffsetY(3);
        xShape.setEffect(shadow);
        group.getChildren().addAll(xShape);
        group.setScaleX(0.7);
        group.setScaleY(0.7);
        return group;
    }

    /**
     * Creates a symbol representing a successful hit in the game.
     *
     * <p>This method creates a visual representation of a successful attack. It generates a bomb icon with a circular body and fuse, along with spark
     * effects. The bomb is created using an image pattern for the body and fuse, and a polygon is used for the spark, with a yellow glow effect.
     * The resulting symbol is added to a `Group` container and returned.</p>
     *
     * @return A `Group` containing the bomb symbol representing a successful hit.
     */

    public Group successSymbol() {
        Group group = new Group();
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/bombfx.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/soga.png")));
        ImagePattern imagePattern5 = new ImagePattern(image5);

        Circle bombBody = new Circle(25, 25, 20);
        bombBody.setFill(Color.DARKSLATEBLUE); // Color gris oscuro con matiz azul
        bombBody.setFill(imagePattern4);
        bombBody.setEffect(new DropShadow(4, Color.BLACK));
        Polygon fuse = new Polygon(25, 5, 40, 0, 42, 2, 26, 7);
        fuse.setFill(imagePattern5);

        Polygon spark = new Polygon(
                28, -12,  // Pico superior largo
                30, -6,  // Pico superior derecho corto
                33, -4,  // Pico derecho largo
                30, -2,  // Pico inferior derecho corto
                28, 0,   // Pico inferior largo
                26, -2,  // Pico inferior izquierdo corto
                23, -4,  // Pico izquierdo largo
                26, -6   // Pico superior izquierdo corto
        );
        spark.setFill(Color.rgb(239, 196, 64));
        spark.setStroke(ORANGE);
        spark.setStrokeWidth(0.5);
        spark.setEffect(new DropShadow(5, Color.YELLOW));

        spark.setScaleX(1.2);
        spark.setScaleY(1.2);
        spark.setTranslateX(15);
        spark.setTranslateY(0);


        group.getChildren().addAll(bombBody, fuse, spark);
        return group;
    }

    /**
     * Handles the victory condition of the game and displays the victory message and video.
     *
     * <p>This method is called when the player wins the game. It displays a victory message on the `infoLabel` and disables the game grid to prevent
     * further interactions. It also stops the main music and plays a victory video. After playing the video, it deletes the game-related files and
     * transitions to the welcome stage.</p>
     *
     * @param victory A boolean indicating whether the player has won.
     */


    public void victory(boolean victory) {
        if (victory) {
            infoLabel.setText("¡Felicidades! Has ganado");
            gridPaneGame.setDisable(true);
            Path path = Paths.get("game.ser");
            Path path2 = Paths.get("save.ser");
            playVideoVictory();
            try {
                Files.delete(path);
                Files.delete(path2);
                System.out.println("El archivo ha sido borrado.");
            } catch (IOException e) {
                System.err.println("Error al borrar el archivo: " + e.getMessage());
            }
        }
    }

    /**
     * Plays the victory video when the player wins the game.
     *
     * <p>This method is responsible for the game and next to the win screen. The video is displayed in full-screen mode (1080p),
     * and after it finishes, the game transitions back to the welcome screen by deleting the current game stage and loading the welcome stage.</p>
     */
    private void playVideoVictory() {
        // Avanzo directamente: borro la instancia de GameStage
        try {
            GameStage.deleteInstance();
        } catch (NullPointerException e2) {
            System.out.println("No se pudo borrar la instancia");
        }

        // Abro la ventana de bienvenida
        try {
            WelcomeStage.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Handles the defeat condition of the game and displays the defeat message and video.
     *
     * <p>This method is called when the player loses the game. It displays a defeat message on the `infoLabel` and disables the game grid to prevent further
     * interactions. it deletes the game-related files and transitions to
     * the welcome stage.</p>
     *
     * @param defeat A boolean indicating whether the player has lost.
     */

    public void defeat(boolean defeat) {
        if (defeat) {
            infoLabel.setText("Has perdido ;(");
            gridPaneGame.setDisable(true);
            Path path = Paths.get("game.ser");
            Path path2 = Paths.get("save.ser");
            playVideoDefeat();
            try {
                Files.delete(path);
                Files.delete(path2);
                System.out.println("El archivo ha sido borrado.");
            } catch (IOException e) {
                System.err.println("Error al borrar el archivo: " + e.getMessage());
            }

        }
    }

    private void playVideoDefeat() {
        // Cierro la ventana de juego
        GameStage.deleteInstance();

        // Abro la ventana de bienvenida
        try {
            WelcomeStage.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Animates the transition of elements into the scene with fade and move effects.
     *
     * <p>This method is used to animate the appearance of two anchor panes (`anchorPaneLeft` and `anchorPaneMiddle`). The left pane slides in from the left
     * side of the screen, and the middle pane slides in from the bottom of the screen. Both panes also have fade-in transitions to make them gradually appear.</p>
     *
     * <p>The method uses the `FadeTransition` and `TranslateTransition` classes to apply these effects in parallel.</p>
     */

    public void animationIn() {

        anchorPaneLeft.setTranslateX(-400);

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        anchorPaneMiddle.setTranslateY(screenWidth);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.9), anchorPaneLeft);
        fadeIn.setFromValue(0.5);
        fadeIn.setToValue(1.0);

        TranslateTransition moveLeft = new TranslateTransition(Duration.seconds(2), anchorPaneLeft);
        moveLeft.setToX(0);

        ParallelTransition newStageTransition = new ParallelTransition(fadeIn, moveLeft);
        newStageTransition.play();

        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2.5), anchorPaneMiddle);
        fadeIn2.setFromValue(0.2);
        fadeIn2.setToValue(1.0);

        TranslateTransition moveUp = new TranslateTransition(Duration.seconds(3), anchorPaneMiddle);
        moveUp.setFromY(screenWidth);
        moveUp.setToY(0);

        ParallelTransition newStageTransition2 = new ParallelTransition(fadeIn2, moveUp);
        newStageTransition2.play();
    }

    /**
     * Creates shadow representations for enemy ships on the game grid.
     *
     * <p>This method iterates over the list of enemy ships and creates shadow rectangles for each cell of the game grid. These shadows represent the positions
     * where enemy ships are placed, but the actual ships are made invisible. The shadows are set up to handle mouse events, allowing the user to interact with
     * them, such as highlighting the shadow on mouse enter, exiting, or clicking on the shadow.</p>
     *
     * @throws IllegalArgumentException if there is an error in adding the ships to the grid.
     */

    public void createEnemyShadows() {
        double cellWidth = 63.7;
        double cellHeight = 63.7;
        gridPaneGame.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/example/miniproyecto3_battleship/Css/css.css")).toExternalForm());
        enemyShips = playerBot.getEnemyShips();
        auxEnemyShips = new ArrayList<>(enemyShips);
        for (int i = 0; i < enemyShips.size(); i++) {
            Ship shipSelected = enemyShips.get(i);
            int row = shipSelected.getPosition()[0] + 1;
            int col = shipSelected.getPosition()[1] + 1;
            try {
                if (shipSelected.isHorizontal()) {
                    gridPaneGame.add(shipSelected, col - shipSelected.getSize() + 1, row);
                    GridPane.setRowSpan(shipSelected, 0);
                    GridPane.setColumnSpan(shipSelected, shipSelected.getSize());
                } else {
                    gridPaneGame.add(shipSelected, col, row - shipSelected.getSize() + 1);
                    GridPane.setColumnSpan(shipSelected, 0);
                    GridPane.setRowSpan(shipSelected, shipSelected.getSize());
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            shipSelected.setVisible(false);
        }
        for (int rows = 1; rows <= 10; rows++) {
            for (int col = 1; col <= 10; col++) {
                Rectangle cell = new Rectangle(cellWidth, cellHeight);
                cell.setFill(Color.TRANSPARENT);
                cell.getStyleClass().add("cell");
                enemyShadow[rows - 1][col - 1] = cell;
                int finalRows = rows - 1;
                int finalCol = col - 1;
                enemyShadow[rows - 1][col - 1].setOnMouseEntered(e -> onHandleMouseEnteredShips(finalRows, finalCol));
                enemyShadow[rows - 1][col - 1].setOnMouseExited(e -> onHandleMouseExitedShips(finalRows, finalCol));
                enemyShadow[rows - 1][col - 1].setOnMouseClicked(e -> onHandleMouseClickedShips(finalRows, finalCol));
                gridPaneGame.add(enemyShadow[rows - 1][col - 1], col, rows);
            }
        }

    }

    /**
     * Handles the mouse entered event for the enemy ship shadows.
     *
     * <p>This method is triggered when the mouse enters a cell representing an enemy ship shadow. It changes the fill of the shadow to a pattern indicating
     * that the player is hovering over it.</p>
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */

    public void onHandleMouseEnteredShips(int row, int col) {

        enemyShadow[row][col].setFill(imagePatter);
    }

    /**
     * Handles the mouse exited event for the enemy ship shadows.
     *
     * <p>This method is triggered when the mouse exits a cell representing an enemy ship shadow. It resets the fill of the shadow back to transparent.</p>
     *
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */

    public void onHandleMouseExitedShips(int row, int col) {
        Color colorDefault = Color.TRANSPARENT;
        enemyShadow[row][col].setFill(colorDefault);
    }

    /**
     * Handles the mouse entered event for the "Show Enemy Ships" button.
     *
     * <p>This method is triggered when the mouse enters the "Show Enemy Ships" button. It changes the button's appearance by applying a hover effect
     * with a new image, indicating to the user that the button is interactive.</p>
     *
     * @param mouseEvent The mouse event that triggered this method.
     */

    @FXML
    public void onHandleMouseEnteredeShowEnemyShips(javafx.scene.input.MouseEvent mouseEvent) {
        Image newImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/buttonHoverShowEnemyShips.png")));
        ImageInput imageInput = new ImageInput(newImage);
        btnShowEnemyShips.setEffect(imageInput);
    }

    /**
     * Handles the mouse exited event for the "Show Enemy Ships" button.
     *
     * <p>This method is triggered when the mouse exits the "Show Enemy Ships" button. It resets the button's appearance back to its original image.</p>
     *
     * @param mouseEvent The mouse event that triggered this method.
     */

    @FXML
    public void onHandleMouseExitedShowEnemyShips(javafx.scene.input.MouseEvent mouseEvent) {
        Image newImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/buttonShowEnemyShips.png")));
        ImageInput imageInput = new ImageInput(newImage);
        btnShowEnemyShips.setEffect(imageInput);
    }

    /**
     * Handles the click event for the "Reset Game" button.
     *
     * <p>This method deletes the current game instance, and navigates the user to the game selection screen
     * to start a new game.</p>
     *
     * @param event The action event triggered by clicking the "Reset Game" button.
     * @throws IOException If an error occurs when navigating to the game selection stage.
     */
    @FXML
    void onHandleClickResetGame(ActionEvent event) throws IOException {
        GameStage.deleteInstance();
        GameSelectionStage.getInstance();
    }

    /**
     * Handles the return action when going back to the welcome stage.
     *
     * <p>This method shows the matrix of the player bot, deletes the current game instance, and navigates the user
     * back to the welcome screen.</p>
     *
     * @param actionEvent The action event triggered by clicking the "Return" button.
     * @throws IOException If an error occurs when navigating to the welcome stage.
     */


    @FXML
    public void onHandleReturn(javafx.event.ActionEvent actionEvent) throws IOException {
        playerBot.showMatrix();
        GameStage.deleteInstance();
        WelcomeStage.getInstance();
    }

}
