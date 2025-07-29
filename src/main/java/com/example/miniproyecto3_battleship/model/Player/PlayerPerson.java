package com.example.miniproyecto3_battleship.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the human player in the Battleship game.
 *
 * <p>The {@code PlayerPerson} class extends {@code APlayer} and implements logic specific
 * to the human player, such as manually setting the ship matrix based on their choices.</p>
 *
 * <p>This class is serializable to allow saving and loading of game states.</p>
 *
 * @author Juan David Lopez V
 */
public class PlayerPerson extends APlayer{

    @Override
    public void setChosenMatrix(int[][] matrix){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                shipsMatrix.get(i).set(j, matrix[i][j]);
            }
        }
    }
}