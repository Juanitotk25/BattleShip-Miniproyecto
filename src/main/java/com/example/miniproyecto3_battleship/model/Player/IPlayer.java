package com.example.miniproyecto3_battleship.model.Player;

import com.example.miniproyecto3_battleship.model.ships.IShip;
import com.example.miniproyecto3_battleship.model.ships.Ship;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public interface IPlayer extends Serializable {
    //setea la matrix inicial
    void setMatrix();
    ArrayList<ArrayList<Integer>> getMatrix();
    //nos va limpiar el tablero
    void clearMatrix();
    //poder cambiar la grilla
    void changeMatrix(int row, int col, int value);
    //permite setear y seleccionar de la grilla
    void setChosenMatrix(int [][] matrix);
    void generateBotGame();
    void botIntelligence(ArrayList<ArrayList<Integer>> matrix);
    void showMatrix();
    boolean verifyWinner();
    void setEnemyShipsInfo(ArrayList<int[]> enemyShipsInfo);
    void generatePositionRandom(ArrayList<ArrayList<Integer>> matrix);
    int getPositionRandom();
    ArrayList<Ship> getEnemyShips();
}
