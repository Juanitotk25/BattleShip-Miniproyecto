package com.example.miniproyecto3_battleship.controller;

import com.example.miniproyecto3_battleship.view.GameSelectionStage;
import com.example.miniproyecto3_battleship.view.WelcomeStage;
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

import java.io.IOException;

public class GameController {

    @FXML
    private GridPane gridPaneGame;
    @FXML
    private Label nameCharacter;
    @FXML
    private ImageView imgCharacter;
    @FXML
    private Button btnRestartFleet;
    @FXML
    private Button btnQuitFleet;
    @FXML
    private Label infoLabel;

    @FXML
    private Rectangle rectangleLabelSelection;
    @FXML
    private Label lbSelecction;
    @FXML
    private Rectangle rectangleLabelInfo;

    // Método de inicialización
    @FXML
    public void initialize() {
        // Establecer la imagen del personaje
        setCharacterImage();

        // Asignar funcionalidad a los botones
        btnRestartFleet.setOnAction(this::onHandleRestartFleet);
        btnQuitFleet.setOnAction(this::onHandleQuitFleet);

        // Establecer el fondo de la pantalla
        setBackground();
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
