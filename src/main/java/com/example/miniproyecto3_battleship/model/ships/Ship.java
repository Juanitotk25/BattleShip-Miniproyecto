package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Represents a ship in the game.
 *
 * <p>The {@code Ship} class defines the basic properties and behaviors of a ship
 * in the Battleship game. It includes attributes such as position, size, orientation
 * (horizontal or vertical), and destruction status. This class provides methods for
 * manipulating the ship's appearance and state, including rotation and selection for placement.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public class Ship extends Pane implements IShip {

    // Group that holds the visual representation of the ship
    protected Group shapesGroup = new Group();
    // A Rectangle representing the body of the ship
    protected Rectangle body = new Rectangle();

    // Flags to manage the ship's state
    protected boolean isSelect;
    protected boolean isHorizontal = true;  // Default orientation is horizontal
    protected int size;
    protected boolean potentialRotate = true;
    protected int[] position = new int[2];  // x and y position
    protected boolean isPlaced = false;
    protected boolean isDestroyed = false;

    // Constructor to initialize the ship's size
    public Ship(int size) {
        this.size = size;
        body.setWidth(100);  // Set default width
        body.setHeight(20);  // Set default height
        this.getChildren().add(body);
        shapesGroup.getChildren().add(body);
    }

    // Apply a glowing effect to the ship to indicate selection
    @Override
    public void selectDesign() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setRadius(15);
        glow.setSpread(0.5);
        shapesGroup.setEffect(glow);
        isSelect = true;
    }

    // Restore the ship's original design and remove the selection effect
    @Override
    public void restoreDesign() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.4);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        glow.setInput(colorAdjust);
        shapesGroup.setEffect(glow);
        isSelect = false;
    }

    // Rotate the ship by 90 degrees (horizontal to vertical, and vice versa)
    @Override
    public void rotateShip() {
        if (isHorizontal) {
            this.setRotate(90);
            isHorizontal = false;
        } else {
            this.setRotate(0);
            isHorizontal = true;
        }
    }

    // Set the orientation of the ship (horizontal or vertical)
    @Override
    public void setOrientation(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    // Set the position of the ship on the board (x, y coordinates)
    @Override
    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    // Get the current position of the ship
    @Override
    public int[] getPosition() {
        return position;
    }

    // Check if the ship is selected
    @Override
    public boolean isSelected() {
        return isSelect;
    }

    // Get the size of the ship (number of cells it occupies)
    @Override
    public int getSize() {
        return size;
    }

    // Check if the ship has been placed on the board
    @Override
    public boolean isPlaced() {
        return isPlaced;
    }

    // Set whether the ship is placed on the board or not
    @Override
    public void setPlaced(boolean isPlaced) {
        this.isPlaced = isPlaced;
    }

    // Check if the ship is oriented horizontally
    @Override
    public boolean isHorizontal() {
        return isHorizontal;
    }

    // Set whether the ship can potentially be rotated or not
    @Override
    public void setCanRotate(boolean canRotate) {
        this.potentialRotate = canRotate;
    }

    // Check if the ship can potentially be rotated
    @Override
    public boolean canRotate() {
        return potentialRotate;
    }

    // Set the selection status of the ship
    @Override
    public void setSelected(boolean isSelected) {
        this.isSelect = isSelected;
    }

    // Set the scale of the ship for rendering
    @Override
    public void setScale(double scaleX, double scaleY) {
        shapesGroup.setScaleX(scaleX);
        shapesGroup.setScaleY(scaleY);
        body.setScaleX(scaleX);
        body.setScaleY(scaleY);
    }

    // Set the destruction status of the ship
    @Override
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    // Check if the ship has been destroyed
    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}

