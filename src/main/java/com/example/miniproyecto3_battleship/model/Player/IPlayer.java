package com.example.miniproyecto3_battleship.model.Player;

import com.example.miniproyecto3_battleship.model.ships.IShip;
import com.example.miniproyecto3_battleship.model.ships.Ship;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IPlayer extends Serializable {

    /**
     * Initializes the player's ship matrix to a 10x10 grid of zeros.
     *
     * <p>This method creates a default game board with no ships placed.</p>
     */
    void setMatrix();


    /**
     * Retrieves the current ship matrix of the player.
     *
     * @return an {@code ArrayList} of {@code ArrayList<Integer>} representing the ship matrix.
     */
    ArrayList<ArrayList<Integer>> getMatrix();

    /**
     * Resets the bot's ship matrix, clearing all ship placements.
     *
     * <p>This method fills the matrix with zeros, representing an empty game board,
     * and reinitializes the ship position matrix for the bot.</p>
     */
    void clearMatrix();


    /**
     * Updates a specific cell in the ship matrix with a new value.
     *
     * @param row the row index of the cell to update.
     * @param col the column index of the cell to update.
     * @param value the new value to set in the specified cell.
     */
    void changeMatrix(int row, int col, int value);

    /**
     * Updates the ship placement matrix based on the player's chosen positions.
     *
     * <p>This method copies the contents of a 2D integer array into the player's
     * internal {@code shipsMatrix}, which represents the game board.</p>
     *
     * @param matrix a 2D array of integers representing the player's chosen ship placements,
     *               where each value indicates whether a cell is empty or occupied by a ship.
     */

    void setChosenMatrix(int [][] matrix);

    /**
     * Generates the bot's ship placements randomly on the game board.
     *
     * <p>This method initializes a predefined number of ships of different sizes, assigns
     * them random positions and orientations, and ensures that no ships overlap.</p>
     *
     * <p>It updates the bot's ship matrix with the placed ships and handles potential
     * exceptions caused by invalid placements.</p>
     */
    void generateBotGame();

    /**
     * Implements the bot's logic for selecting attack positions.
     *
     * <p>This is an abstract method to be implemented by subclasses.</p>
     *
     * @param matrix the game board matrix for decision-making.
     */

    void botIntelligence(ArrayList<ArrayList<Integer>> matrix);


    /**
     * Displays the ship matrix in the console for debugging purposes.
     *
     * <p>The method prints the matrix row by row, with each cell's value
     * separated by a space.</p>
     */

    void showMatrix();

    /**
     * Verifies if the player has won the game.
     *
     * <p>This method counts the number of cells marked as hits (-1) and checks if
     * they match the total number of ship cells (20).</p>
     *
     * @return {@code true} if the player has won, {@code false} otherwise.
     */
    boolean verifyWinner();

    /**
     * Sets the enemy ships' information for the bot.
     *
     * @param enemyShipsInfo a list of integer arrays where each array represents a ship's
     *                       details: row, column, size, orientation, and destruction status.
     */
    void setEnemyShipsInfo(ArrayList<int[]> enemyShipsInfo);

    /**
     * Generates a random attack position on the game board.
     *
     * <p>The method ensures the selected position is valid and not previously attacked.
     * It updates the bot's {@code positionAttack} attribute with the selected coordinates.</p>
     *
     * @param matrix the current game board matrix representing attacked and occupied cells.
     */
    void generatePositionRandom(ArrayList<ArrayList<Integer>> matrix);

    /**
     * Retrieves the bot's most recently generated attack position.
     *
     * @return an array of two integers representing the row and column of the attack position.
     */
    int[] getPositionRandom();

    /**
     * Retrieves a list of enemy ships based on the bot's stored information.
     *
     * <p>The method constructs ship objects (e.g., {@code Fragata}, {@code Destructor},
     * {@code Submarino}, {@code Portaaviones}) from the stored data, sets their positions,
     * and returns the complete list.</p>
     *
     * @return an {@code ArrayList} of {@code Ship} objects representing the enemy's ships.
     */
    ArrayList<Ship> getEnemyShips();
}
