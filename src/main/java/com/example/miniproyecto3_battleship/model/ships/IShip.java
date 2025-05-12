package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;


public interface IShip {
    void selectDesing();

    void originDesing();

    void rotateShip();

    void setIsHorizontal(boolean isHorizontal);

    void setPosition(int x, int y);


    int[] getPosition();

    boolean isSelect();

    int getSize();

    boolean isPlaced();

    void setIsPlaced(boolean isPlaced);

    boolean isHorizontal();

    void setPotentialRotate(boolean potentialRotate);

    boolean potentialRotate();

    void setIsSelect(boolean isSelect);

    void setScaleShip(double X, double Y);

    void setIsDestroyed(boolean isDestroyed);

    boolean isDestroyed();

}