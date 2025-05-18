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
