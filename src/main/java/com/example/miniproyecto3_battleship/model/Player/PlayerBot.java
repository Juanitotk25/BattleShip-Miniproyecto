package com.example.miniproyecto3_battleship.model.Player;

import java.util.ArrayList;
import java.util.Stack;

public class PlayerBot extends APlayer {

    int[] positionAttack = new int[2];
    Stack<int[]> shots = new Stack<>();
    private ArrayList<int[]> enemyShipsInfo = new ArrayList<>();

    public void generaBotGame(){
        for(int i = 0; i < 4; i++){
            enemyShipsInfo.add(new int[]{0, 0, 1, 0, 0});
        }
    }
}
