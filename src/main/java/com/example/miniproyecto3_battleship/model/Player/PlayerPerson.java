package com.example.miniproyecto3_battleship.model.Player;

import java.io.Serializable;
import java.util.ArrayList;

//esrta clase repreenta al usuario en el juego y su funcionalidad.
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