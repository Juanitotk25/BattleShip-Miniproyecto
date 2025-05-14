package com.example.miniproyecto3_battleship.model.Player;

import com.example.miniproyecto3_battleship.model.ships.IShip;

import java.io.Serializable;
import java.util.ArrayList;

public interface IPlayer extends Serializable {

    void setMatrix();
    ArrayList<ArrayList<Integer>> getMatrix();
    void clearMatrix();
    void changeMatrix(int row, int col, int value);
    void setChosenMatrix(int [][] matrix);

}
