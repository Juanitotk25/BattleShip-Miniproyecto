package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


/**
 * Represents the contract for all ship types in the Battleship game.
 * <p>This interface defines the methods required for managing the visual representation,
 * orientation, placement, and status of ships in the game.</p>
 *
 * @author Nicolas Cordoba
 * @author Samuel Arenas
 * @author Juan Manuel Ampudia
 */

public interface IShip {
    /**
     * Applies a glowing effect to the ship to indicate it is selected.
     *
     * <p>This method adds a white glow effect to the ship to visually represent that
     * it has been selected. It also sets the {@code isSelect} flag to {@code true}.</p>
     */
    void selectDesing();

    /**
     * Restores the ship's original design and removes the selection effect.
     *
     * <p>This method removes the glow effect applied during selection and resets the
     * ship's visual appearance to its original state. It also sets the {@code isSelect}
     * flag to {@code false}.</p>
     */
    void originDesing();

    /**
     * Rotates the ship's orientation by 90 degrees.
     *
     * <p>If the ship is currently horizontal, this method will rotate it to a vertical
     * orientation, and vice versa. It updates the {@code isHorizontal} flag accordingly.</p>
     */
    void rotateShip();

    /**
     * Sets the ship's orientation.
     *
     * <p>This method sets the {@code isHorizontal} flag to the specified value,
     * which determines whether the ship is placed horizontally or vertically.</p>
     *
     * @param isHorizontal a boolean value indicating whether the ship should be
     *                     oriented horizontally (true) or vertically (false).
     */
    void setIsHorizontal(boolean isHorizontal);

    /**
     * Sets the position of the ship on the game board.
     *
     * <p>This method updates the {@code position} array with the specified coordinates
     * for the ship's placement on the board.</p>
     *
     * @param x the row coordinate of the ship's position.
     * @param y the column coordinate of the ship's position.
     */
    void setPosition(int x, int y);

    /**
     * Retrieves the current position of the ship.
     *
     * <p>This method returns the {@code position} array containing the ship's current
     * coordinates (row and column).</p>
     *
     * @return an array of two integers, where the first element is the row and the
     *         second element is the column of the ship's position.
     */
    int[] getPosition();

    /**
     * Checks whether the ship is currently selected.
     *
     * <p>This method returns the value of the {@code isSelect} flag, which indicates
     * if the ship is currently selected for placement or manipulation.</p>
     *
     * @return {@code true} if the ship is selected, {@code false} otherwise.
     */
    boolean isSelect();

    /**
     * Retrieves the size of the ship.
     *
     * <p>This method returns the size of the ship, which corresponds to the number
     * of cells the ship occupies on the game board.</p>
     *
     * @return the size of the ship as an integer.
     */
    int getSize();

    /**
     * Checks whether the ship has been placed on the game board.
     *
     * <p>This method returns the value of the {@code isPlaced} flag, which indicates
     * whether the ship has been placed on the board.</p>
     *
     * @return {@code true} if the ship has been placed, {@code false} otherwise.
     */
    boolean isPlaced();

    /**
     * Sets the placement status of the ship.
     *
     * <p>This method updates the {@code isPlaced} flag to the specified value,
     * indicating whether the ship has been placed on the board or not.</p>
     *
     * @param isPlaced a boolean value indicating whether the ship has been placed
     *                 on the board ({@code true}) or not ({@code false}).
     */
    void setIsPlaced(boolean isPlaced);

    /**
     * Checks whether the ship is placed horizontally.
     *
     * <p>This method returns the value of the {@code isHorizontal} flag, which indicates
     * whether the ship is currently placed horizontally or vertically.</p>
     *
     * @return {@code true} if the ship is horizontal, {@code false} if it is vertical.
     */
    boolean isHorizontal();

    /**
     * Sets whether the ship has the potential to be rotated.
     *
     * <p>This method updates the {@code potentialRotate} flag, which determines
     * whether the ship can be rotated based on its current position and orientation.</p>
     *
     * @param potentialRotate a boolean value indicating whether the ship can be rotated.
     */
    void setPotentialRotate(boolean potentialRotate);

    /**
     * Checks if the ship has the potential to be rotated.
     *
     * <p>This method returns the value of the {@code potentialRotate} flag, which indicates
     * whether the ship can be rotated based on its current position.</p>
     *
     * @return {@code true} if the ship can be rotated, {@code false} otherwise.
     */
    boolean potentialRotate();

    /**
     * Sets the selection status of the ship.
     *
     * <p>This method updates the {@code isSelect} flag to the specified value,
     * indicating whether the ship is selected or not.</p>
     *
     * @param isSelect a boolean value indicating whether the ship is selected.
     */
    void setIsSelect(boolean isSelect);

    /**
     * Sets the scale of the ship for rendering.
     *
     * <p>This method scales the ship's visual representation by the specified factors
     * for the X and Y axes.</p>
     *
     * @param X the scaling factor for the X-axis (width).
     * @param Y the scaling factor for the Y-axis (height).
     */
    void setScaleShip(double X, double Y);

    /**
     * Sets the destruction status of the ship.
     *
     * <p>This method updates the {@code isDestroyed} flag, which indicates whether
     * the ship has been destroyed or not.</p>
     *
     * @param isDestroyed a boolean value indicating whether the ship is destroyed.
     */
    void setIsDestroyed(boolean isDestroyed);

    /**
     * Checks whether the ship has been destroyed.
     *
     * <p>This method returns the value of the {@code isDestroyed} flag, which indicates
     * whether the ship has been destroyed after being hit.</p>
     *
     * @return {@code true} if the ship is destroyed, {@code false} otherwise.
     */
    boolean isDestroyed();

}