package com.example.miniproyecto3_battleship.model.Serializable;

import com.example.miniproyecto3_battleship.model.Game.Game;
import com.example.miniproyecto3_battleship.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a saved state of the game, specifically the positions of the player ships.
 *
 * <p>The {@code Save} class is used to store the positions and status of ships
 * during a game session. This data can be serialized to persist the game state.</p>
 *
 * <p>This class allows the saving and retrieval of ship information, such as their
 * position, size, orientation, and whether they are destroyed.</p>
 *
 * @author Juan David Lopez V
 */
public class Save implements Serializable {
    private ArrayList<int[]> shipPositions;

    /**
     * Constructs a new {@code Save} object with the given ship positions.
     *
     * <p>This constructor initializes the saved state with the positions of the ships,
     * where each position is represented by an array containing information like
     * coordinates, size, orientation, and destruction status.</p>
     *
     * @param ship the list of ship positions to be saved, where each element is an
     *             array containing position details: row, column, size, orientation, and destruction status.
     */
    public Save(ArrayList<int[]> ship) {
        this.shipPositions = ship;
    }


    /**
     * Sets the ship positions for the saved state.
     *
     * <p>This method allows updating the ship positions in the saved state.</p>
     *
     * @param shipPositions the new list of ship positions to be saved.
     */
    public void setShipPositions(ArrayList<int[]> shipPositions) {
        this.shipPositions = shipPositions;
    }

    /**
     * Converts the saved ship positions into a list of {@code Ship} objects.
     *
     * <p>This method translates the stored ship positions (in the form of an array of integers)
     * into actual {@code Ship} objects, creating instances such as {@code Fragata}, {@code Destructor},
     * {@code Submarino}, and {@code Portaaviones} based on the stored data. It also sets the
     * position, orientation, and destruction status of each ship.</p>
     *
     * @return an {@code ArrayList} of {@code Ship} objects representing the ships in the saved state.
     */
    public ArrayList<Ship> getShip() {
        ArrayList<Ship> ships = new ArrayList<>();
        for(int i = 0; i < shipPositions.size(); i++) {
            int[] shipInfo = this.shipPositions.get(i);
            int row = shipInfo[0];
            int col = shipInfo[1];
            int size = shipInfo[2];
            int isHorizontal = shipInfo[3];
            int isDestroyed = shipInfo[4];

            Ship shipSelected = new Ship();

            if (size == 1) {
                shipSelected = new Fragata();
            }
            if (size == 2) {
                shipSelected = new Destructor();
            }
            if (size == 3) {
                shipSelected = new Submarino();
            }
            if (size == 4) {
                shipSelected = new Portaaviones();
            }
            if (isHorizontal != 1) {
                shipSelected.rotateShip();
            }
            if(isDestroyed == 1){
                shipSelected.setIsDestroyed(true);
            }
            shipSelected.setPosition(row, col);
            ships.add(shipSelected);
        }
        return ships;
    }
}
