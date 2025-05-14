package com.example.miniproyecto3_battleship.model.Player;

import com.example.miniproyecto3_battleship.model.ships.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class PlayerBot extends APlayer{

    int[] positionAttack = new int[2];
    Stack<int[]> shots = new Stack<>();
    private ArrayList<int[]> enemyShipsInfo = new ArrayList<>(); // [0] = x, [1] = y, [2] = size, [3] = orientation


    public void generateBotGame() {
        for (int i = 0; i < 4; i++) {
            enemyShipsInfo.add(new int[]{0, 0, 1, 0, 0});
        }

        for (int i = 0; i < 3; i++) {
            enemyShipsInfo.add(new int[]{0, 0, 2, 0, 0});
        }

        for (int i = 0; i < 2; i++) {
            enemyShipsInfo.add(new int[]{0, 0, 3, 0, 0});
        }

        for (int i = 0; i < 1; i++) {
            enemyShipsInfo.add(new int[]{0, 0, 4, 0, 0});
        }

        int row, col, randomHorientation;
        boolean tryAgain;
        for (int i = 0; i < enemyShipsInfo.size(); i++) {
            do {
                tryAgain = false;
                row = (int) (Math.random() * 9);
                col = (int) (Math.random() * 9);
                Random random = new Random();
                randomHorientation = random.nextInt(2);
                enemyShipsInfo.get(i)[0] = row;
                enemyShipsInfo.get(i)[1] = col;
                enemyShipsInfo.get(i)[3] = randomHorientation;
                for (int j = 0; j < enemyShipsInfo.get(i)[2]; j++) {
                    try {
                        if (randomHorientation == 1) {
                            if (shipsMatrix.get(row).get(col - j) != 0) {
                                tryAgain = true;
                            }
                        } else {
                            if (shipsMatrix.get(row - j).get(col) != 0) {
                                tryAgain = true;
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        tryAgain = true;
                    }
                }
            } while (tryAgain);
            for (int j = 0; j < enemyShipsInfo.get(i)[2]; j++) {
                if (enemyShipsInfo.get(i)[3] == 1) {
                    shipsMatrix.get(row).set(col - j, 1);
                } else {
                    shipsMatrix.get(row - j).set(col, 1);
                }
            }
        }
    }

    public void setEnemyShipsInfo(ArrayList<int[]> enemyShipsInfo) {
        this.enemyShipsInfo = enemyShipsInfo;
    }

    public void generatePositionRandom(ArrayList<ArrayList<Integer>> matrix) {
        int actualPosition;
        do {
            positionAttack[0] = (int) (Math.random() * 10) + 1;;
            positionAttack[1] = (int) (Math.random() * 10) + 1;;
            actualPosition = matrix.get(positionAttack[0]-1).get(positionAttack[1]-1);
            System.out.println(actualPosition);
        } while (actualPosition != 0 && actualPosition != 1);
    }

    public int[] getPositionRandom() {
        return positionAttack;
    }

    public ArrayList<Ship> getEnemyShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        for (int i = 0; i < enemyShipsInfo.size(); i++) {
            int[] shipInfo = this.enemyShipsInfo.get(i);
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
            shipSelected.setPosition(row, col);
            ships.add(shipSelected);
        }
        return ships;
    }


    public void clearMatrix() {
        for (ArrayList<Integer> fila : shipsMatrix) {
            Collections.fill(fila, 0);
        }
        Integer[][] shipsPositionMatrixBot = new Integer[10][10];
    }
}
