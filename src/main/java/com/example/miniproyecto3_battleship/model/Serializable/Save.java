package com.example.miniproyecto3_battleship.model.Serializable;

import com.example.miniproyecto3_battleship.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
    private ArrayList<int[]> shipPositions;

    public Save(ArrayList<int[]> ship) {
        this.shipPositions = ship;
    }

}
