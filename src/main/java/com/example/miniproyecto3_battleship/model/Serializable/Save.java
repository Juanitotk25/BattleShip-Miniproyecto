package com.example.miniproyecto3_battleship.model.Serializable;

import com.example.miniproyecto3_battleship.model.Game.Game;
import com.example.miniproyecto3_battleship.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
    private ArrayList<int[]> shipPositions;

    public Save(ArrayList<int[]> ship) {
        this.shipPositions = ship;
    }

    public void setShipPositions(ArrayList<int[]> shipPositions) {
        this.shipPositions = shipPositions;
    }

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
