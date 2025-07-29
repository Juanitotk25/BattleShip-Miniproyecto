package com.example.miniproyecto3_battleship.model.Player;

import com.example.miniproyecto3_battleship.model.ships.Ship;

import java.util.ArrayList;

/**
 * Abstract class representing a generic player in the Battleship game.
 *
 * <p>The {@code APlayer} class provides a base implementation for both human and bot players,
 * including common functionalities such as managing the ship matrix and verifying the winner.</p>
 *
 * <p>Subclasses must implement specific behaviors for their player types.</p>
 *
 * <p>The ship matrix is represented as a 2D {@code ArrayList} of integers, where each cell indicates
 * the state (e.g., empty, occupied, or hit).</p>
 *
 * @author Juan David Lopez v
 */

public abstract class APlayer implements IPlayer {
    protected final ArrayList<ArrayList<Integer>> shipsMatrix = new ArrayList<>();

    @Override
    public void setMatrix() {
        int filas = 10;
        int columnas = 10;
        for (int i = 0; i < filas; i++) {
            ArrayList<Integer> fila = new ArrayList<>();
            for (int j = 0; j < columnas; j++) {
                fila.add(0);
            }
            this.shipsMatrix.add(fila);
        }
    }

    @Override
    public ArrayList<ArrayList<Integer>> getMatrix() {
        return shipsMatrix;
    }

    @Override
    public void clearMatrix() {}


    @Override
    public void changeMatrix(int row, int col, int value) {
        shipsMatrix.get(row).set(col, value);
    }

    @Override
    public void setChosenMatrix(int [][] matrix){}

    @Override
    public void generateBotGame(){}

    @Override
    public void botIntelligence(ArrayList<ArrayList<Integer>> matrix){}

    //recorre la matrix bidimensional e imprime
    public void showMatrix(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(shipsMatrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }


    public boolean verifyWinner(){
        int counter = 0;
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(shipsMatrix.get(i).get(j) == -1){
                    counter++;
                }
            }
        }
        return counter == 20;
    }

    public void setEnemyShipsInfo(ArrayList<int[]> enemyShipsInfo){}

    public void generatePositionRandom(ArrayList<ArrayList<Integer>> matrix){}


    public int[] getPositionRandom(){return new int[2]; }

    public ArrayList<Ship> getEnemyShips(){
        return new ArrayList<>();
    }
}
