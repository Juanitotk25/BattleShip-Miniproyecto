package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.model.Player.PlayerBot;
import com.example.miniproyecto3_battleship.model.Player.PlayerPerson;
import com.example.miniproyecto3_battleship.model.Game.Game;
import com.example.miniproyecto3_battleship.model.Serializable.ISerializableFileHandler;
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
    private Image expls = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto_3_battlership/Image/explosion.png")));
    ImagePattern imagePattern = new ImagePattern(expls);

    private String nameCharacterActual;
    private String nameEnemyActual;
    private int imageEnemyActual;

    private int rowBot, columnbot, countShow;

    private PlainTextFileHandler plainTextFileHandler;

    //Metodo de inicialización del stage y su correspondiente asignacion de fondo y demás
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

    //este metodo hace que setea las grillas del bot y el usuario que seleccióno los barcos
    //además serializa el estatus del juego y guarda los datos para un uso futuro
    public void setGridPaneShips(ArrayList<int[]> shipsPositions, int[][] shipsSelected) {

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


    //organiza el ambiente del jugador y el bot de donde lo dejaron en la ultima sesion registrada
    //por esas las lineas de deserialización en los archivos save.ser y game.ser
    //restaura los barcos del bot y jugador donde estaban
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

    //este metodo setea las grillas para el juego, ajustando los barcos y su posicionamiento
    //ajusta tambien las celdas para que se adecuen correctamente los barcos en su orientación correcta
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


    //este metodo extrae la orientación, tamaño y posición de cada barco
    //devuelve las caracteristicas anteriores en forma de una lista de barcos
    //y la info es retornada como lista de arreglos enteros donde cada array es info de un barco
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

    // este metodo itera a traves de la lista de los barcos enemigos y cambia
    //el estatus de la visibilidad de un barco si no son destruidos, usa el metodo isDestroyed
    @FXML
    public void onHandlePutEnemyShips() {
        for (int i = 0; i < enemyShips.size(); i++) {
            if (!(enemyShips.get(i).isDestroyed())) {
                enemyShips.get(i).setVisible(!enemyShips.get(i).isVisible());
            }
        }
    }

    //asigna un enemigo random de la lista de nombres y una de las dos imagenes para enemigos
    //se utilizan solos dos imagenes para no ocupar mas espacio en el proyecto
    //además serializa y guarda los datos para que se guarde el juego
    public void setEnemy() {
        if (Objects.equals(nameEnemyActual, " ")) {
            String[] enemys = {"Gul'dan", "Arthas", "Illidan", "Kill'jaeden"};
            nameEnemyActual = enemys[(int) (Math.random() * 3)];
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

    //este metodo es como el setEnemy solo que aplica para el setChracter del usuario, es decir
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


    //este metodo se encarga del status de una celda de la grilla cuando el jugador dispara
    // si pega a un parte del barco, la celda se actualiza con un simbolo de exito y sigue turno usuario
    //si falla el tiro, se pone un simbolo de eror en la casilla y es turno del bot
    //tambien chequea si un barco es destruido y actualiza el status del juego
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
                infoLabel.setText("Felicidades, atinaste, tira nuevamente! ");
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
                infoLabel.setText("Sos muy malo, deja a tu oponente atacar");
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


    //Este metodo revisa y chequea si un barco ha sido destruido revisando sus partes
    //si es destruido, actualiza la grilla y refleja con un efecto que el barco fue destruido
    //tambien guarda el status del juego y datos
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

    //este metodo actualiza la imagen de cuando golpea un barco
    //setea la imagen de un personaje de fuego
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

    //carga los barcos del jugador en la UI e itera a traves de la matrix del jugador
    //un valor de 2 es que falló y actualiza con error
    // un valor de -1 es que atacó correctamente y llama al metodo DestroyedEnemyShip
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

    //este metodo chequea si todas las partes de un barco han sido destruidas y actualiza
    //la grilla reflejando la destruccion del barco,tambien el barco es removido de la lista
    // de barcos
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
                infoLabel.setText("Por fin destruiste un barco!");
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


    // Método para establecer el fondo de la pantalla
    private void setBackground() {
        Image backgroundImage = new Image(getClass().getResource("/com/example/miniproyecto3_battleship/Image/background_game.png").toExternalForm());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false)
        );
        gridPaneGame.setBackground(new Background(background));
    }

    // Método para cambiar la imagen del personaje
    private void setCharacterImage() {
        // Supongamos que el nombre del personaje está almacenado en `nameCharacter`
        String characterName = nameCharacter.getText();
        Image characterImage = new Image(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/" + characterName + ".png"));
        imgCharacter.setImage(characterImage);
    }

    // Acción para el botón "Reiniciar flota"
    @FXML
    public void onHandleRestartFleet(ActionEvent event) {
        System.out.println("Reiniciando la flota...");

        // Aquí podrías agregar la lógica para reiniciar la flota (por ejemplo, limpiar el tablero o colocar los barcos de nuevo)
        //resetGrid();
        infoLabel.setText("Flota reiniciada, selecciona nuevamente los barcos.");
    }

    // Acción para el botón "Abandonar flota"
    @FXML
    public void onHandleQuitFleet(ActionEvent event) {
        System.out.println("Abandonando la flota...");

        // Regresar al menú principal o a la pantalla de selección de juego
        try {
            GameSelectionStage.deleteInstance();  // Cierra la pantalla actual
            WelcomeStage.getInstance();           // Abre la pantalla de bienvenida o menú principal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para reiniciar el grid de juego (limpiar el tablero)
    /*private void resetGrid() {
        for (int row = 0; row < gridPaneGame.getRowCount(); row++) {
            for (int col = 0; col < gridPaneGame.getColumnCount(); col++) {
                // Limpia las celdas del tablero (asumiendo que las celdas son Rectangles o algún tipo de visualización)
                Node cell = getNodeByRowColumnIndex(row, col);
                if (cell != null) {
                    // Por ejemplo, si las celdas son Rectangles, restablecer el color de fondo a TRANSPARENT
                    ((Rectangle) cell).setFill(Color.TRANSPARENT);
                }
            }
        }
    }*/

    /*// Método para obtener un nodo específico en la posición de la fila y columna del grid
    private Node getNodeByRowColumnIndex(final int row, final int column) {
        Node result = null;
        ObservableList<Node> children = gridPaneGame.getChildren();
        for (Node node : children) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }*/
}
