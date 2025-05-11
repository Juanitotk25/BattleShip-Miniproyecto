package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

/**
 * Represents the contract for all ship types in the Battleship game.
 * <p>This interface defines the methods required for managing the visual representation,
 * orientation, placement, and status of ships in the game.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public interface IShip {

    void selectDesign();

    void restoreDesign();

    void rotateShip();

    void setOrientation(boolean isHorizontal);

    void setPosition(int x, int y);

    int[] getPosition();

    boolean isSelected();

    int getSize();

    boolean isPlaced();

    void setPlaced(boolean isPlaced);

    boolean isHorizontal();

    boolean canRotate();

    void setCanRotate(boolean canRotate);

    /**
     * Updates the selection status of the ship.
     *
     * @param isSelected true if the ship is selected, false otherwise.
     */
    void setSelected(boolean isSelected);

    /**
     * Scales the ship's visual representation.
     *
     * @param scaleX the scale factor for the X-axis (width).
     * @param scaleY the scale factor for the Y-axis (height).
     */
    void setScale(double scaleX, double scaleY);

    /**
     * Sets the destruction status of the ship.
     *
     * @param isDestroyed true if the ship is destroyed, false otherwise.
     */
    void setDestroyed(boolean isDestroyed);

    /**
     * Checks if the ship is destroyed.
     *
     * @return true if the ship is destroyed, false otherwise.
     */
    boolean isDestroyed();
}
