package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.Serializable;

/**
 * Represents a ship in the game.
 *
 * <p>The {@code Ship} class defines the basic properties and behaviors of a ship
 * in the Battleship game. It includes attributes such as position, size, orientation
 * (horizontal or vertical), and destruction status. This class provides methods for
 * manipulating the ship's appearance and state, including rotation and selection for placement.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public class Ship extends Pane implements IShip{
    protected Group shapesGroup = new Group();
    protected Rectangle body = new Rectangle();
    protected int size;
    protected boolean potentialRotate = true;
    protected int[] position = new int[2];
    protected boolean isPlaced = false;
    protected boolean isDestroyed = false;
    protected boolean isSelect;
    protected boolean isHorizontal = true;

    public void selectDesing() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setRadius(15);
        glow.setSpread(0.5);
        shapesGroup.setEffect(glow);
        isSelect = true;
    }

    public void originDesing() {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.4);
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0);
        glow.setInput(colorAdjust);
        shapesGroup.setEffect(glow);
        isSelect = false;
    }

    public void rotateShip() {
        if(isHorizontal) {
            this.setRotate(90);
            isHorizontal = false;
        }else{
            this.setRotate(0);
            isHorizontal = true;
        }
    }


    public void setPosition(int x, int y) {
        position[0] = x;
        position[1] = y;
    }

    public void setIsHorizontal(boolean isHorizontal){
        this.isHorizontal = isHorizontal;
    }

    public void setIsPlaced(boolean isPlaced){
        this.isPlaced = isPlaced;
    }

    public boolean isPlaced(){
        return isPlaced;
    }

    public int[] getPosition() {
        return position;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public int getSize() {
        return size;
    }

    public boolean potentialRotate(){
        return potentialRotate;
    }

    public boolean isHorizontal(){
        return isHorizontal;
    }

    public void setPotentialRotate(boolean potentialRotate){
        this.potentialRotate = potentialRotate;
    }

    public void setIsSelect(boolean isSelect){
        this.isSelect = isSelect;
    }

    public void setScaleShip(double X, double Y){
        shapesGroup.setScaleX(X);
        shapesGroup.setScaleY(Y);
        body.setScaleX(X);
        body.setScaleY(Y);
    }

    public void setIsDestroyed(boolean isDestroyed){
        this.isDestroyed = isDestroyed;
    }

    public boolean isDestroyed(){
        return isDestroyed;
    }

}

