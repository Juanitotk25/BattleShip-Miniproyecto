package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Portaaviones extends Ship {

    public Portaaviones() {
        Polygon portaavionesShape1 = new Polygon();
        Polygon portaavionesShape2 = new Polygon();
        Polygon portaavionesShape3 = new Polygon();
        Polygon portaavionesShape4 = new Polygon();
        Polygon portaavionesShape5 = new Polygon();
        Polygon portaavionesShape6 = new Polygon();
        Polygon portaavionesShape7 = new Polygon();
        Polygon portaavionesShape8 = new Polygon();
        Polygon portaavionesShape9 = new Polygon();
        Polygon portaavionesShape10 = new Polygon();
        Polyline portaavionesShape11 = new Polyline();
        Polygon portaavionesShape12 = new Polygon();
        Polygon portaavionesShape13 = new Polygon();
        Polygon portaavionesShape14 = new Polygon();
        portaavionesShape1.getPoints().addAll(
                0.0,30.0,
                0.0,20.0,
                5.0,10.0,
                80.0,10.0,
                86.0,9.0,
                86.0+8,8.0,
                86.0+(8*2),7.0,
                86.0+(8*3),6.0,
                86.0+(8*4),5.0,
                86.0+(8*5),4.0,
                86.0+(8*6),3.0,
                86.0+(8*7),2.0,
                86.0+(8*8),1.0,
                86.0+(8*9)+2,0.0,
                160.0,8.0,
                163.0,8.0,
                163.0,15.0,
                166.0,15.0,
                166.0,20.0,
                185.0,20.0,
                185.0,22.0,
                195.0,22.0,
                195.0,24.0,
                205.0,24.0,
                205.0,26.0,
                215.0,26.0,
                215.0,30.0,
                235.0,30.0,
                235.0,40.0,
                215.0,40.0,
                215.0,44.0,
                205.0,44.0,
                205.0,46.0,
                195.0,46.0,
                195.0,48.0,
                185.0,48.0,
                185.0,50.0,
                165.0,50.0,
                160.0,55.0,
                160.0,60.0,
                145.0,60.0,
                145.0,50.0,
                135.0,50.0,
                135.0,53.0,
                120.0,53.0,
                120.0,50.0,
                80.0,50.0,
                80.0,55.0,
                78.0,55.0,
                75.0,50.0,
                2.0,50.0,
                2.0,40.0,
                1.0,40.0,
                1.0,30.0
        );
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture2.png")));
        ImagePattern imagePattern = new ImagePattern(image);
        portaavionesShape1.setFill(imagePattern);
        portaavionesShape1.setStroke(Color.BLACK);

        portaavionesShape2.getPoints().addAll(
                20.0,55.0,
                35.0,55.0,
                35.0,50.0,
                30.0,47.0,
                15.0,47.0,
                15.0,53.0,
                20.0,53.0
        );
        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata4.png")));
        ImagePattern imagePattern7 = new ImagePattern(image7);
        portaavionesShape2.setFill(imagePattern7);
        portaavionesShape2.setStroke(Color.BLACK);

        portaavionesShape3.getPoints().addAll(
                35.0,53.0,
                40.0,53.0,
                40.0,52.0,
                45.0,52.0,
                45.0,50.0
        );
        portaavionesShape3.setFill(Color.GREEN);
        portaavionesShape3.setStroke(Color.BLACK);

        portaavionesShape4.getPoints().addAll(
                45.0,60.0,
                65.0,60.0,
                65.0,40.0,
                45.0,40.0
        );
        portaavionesShape4.setFill(Color.DARKGRAY);
        portaavionesShape4.setStroke(Color.BLACK);

        portaavionesShape5.getPoints().addAll(
                45.0+2,60.0-2,
                65.0-2,60.0-2,
                65.0-2,40.0+2,
                45.0+2,40.0+2
        );
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture3.png")));
        ImagePattern imagePattern5 = new ImagePattern(image5);
        portaavionesShape5.setFill(imagePattern5);
        portaavionesShape5.setStroke(Color.BLACK);

        portaavionesShape6.getPoints().addAll(
                85.0,50.0,
                83.0,48.0,
                83.0,44.0,
                85.0,42.0,
                105.0,42.0,
                105.0,40.0,
                115.0,40.0,
                115.0,50.0
        );
        Image image8 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture6.png")));
        ImagePattern imagePattern8 = new ImagePattern(image8);
        portaavionesShape6.setFill(imagePattern8);
        portaavionesShape6.setStroke(Color.BLACK);

        portaavionesShape7.getPoints().addAll(
                145.0,60.0,
                160.0,60.0,
                160.0,45.0,
                145.0,45.0
        );
        portaavionesShape7.setFill(Color.DARKGRAY);
        portaavionesShape7.setStroke(Color.BLACK);

        portaavionesShape8.getPoints().addAll(
                145.0+2,60.0-2,
                160.0-2,60.0-2,
                160.0-2,45.0+2,
                145.0+2,45.0+2
        );
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture3.png")));
        ImagePattern imagePattern6 = new ImagePattern(image6);
        portaavionesShape8.setFill(imagePattern6);
        portaavionesShape8.setStroke(Color.BLACK);

        portaavionesShape9.getPoints().addAll(
                120.0,35.0,
                230.0,35.0
        );
        portaavionesShape9.setStroke(Color.SANDYBROWN);
        portaavionesShape9.setOpacity(0.5);

        portaavionesShape10.getPoints().addAll(
                180.0,40.0,
                195.0,40.0,
                195.0,30.0,
                180.0,30.0
        );
        Image image9 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata4.png")));
        ImagePattern imagePattern9 = new ImagePattern(image9);
        portaavionesShape10.setFill(imagePattern9);

        portaavionesShape11.getPoints().addAll(
                15.0,10.0,
                15.0,7.0,
                20.0,7.0,
                20.0,5.0,
                110.0,5.0,
                80.0,10.0
        );
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata1.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        portaavionesShape11.setFill(imagePattern4);
        portaavionesShape11.setStroke(Color.BLACK);

        portaavionesShape12.getPoints().addAll(
                3.0,20.0,
                158.0,5.0,
                158.0,28.0,
                7.0,43.0,
                7.0,39.0,
                5.0,39.0,
                5.0,29.0,
                3.0,29.0
        );
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata1.png")));
        ImagePattern imagePattern2 = new ImagePattern(image2);
        portaavionesShape12.setFill(imagePattern2);
        portaavionesShape12.setStroke(Color.TRANSPARENT);

        portaavionesShape13.getPoints().addAll(
                3.0+4,20.0+4,
                158.0-4,5.0+4,
                158.0-4,28.0-4,
                7.0+4,43.0-4,
                7.0+3,39.0-1,
                5.0+4,39.0-1,
                5.0+4,29.0,
                3.0+2,29.0
        );
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture5.png")));
        ImagePattern imagePattern3 = new ImagePattern(image3);
        portaavionesShape13.setFill(imagePattern3);
        portaavionesShape13.setStroke(Color.TRANSPARENT);

        portaavionesShape14.getPoints().addAll(
                15.0,32.0,
                150.0,16.0
        );

        portaavionesShape14.setStroke(Color.WHITE);
        portaavionesShape14.setOpacity(0.5);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.3);
        shapesGroup.setEffect(glow);
        shapesGroup.setStyle("-fx-cursor: hand");
        shapesGroup.setLayoutX(10);
        shapesGroup.setLayoutY(2);
        shapesGroup.setScaleX(1.1);
        shapesGroup.setScaleY(1.1);

        shapesGroup.getChildren().addAll(portaavionesShape1, portaavionesShape2, portaavionesShape3, portaavionesShape4,portaavionesShape5, portaavionesShape6,portaavionesShape7, portaavionesShape8, portaavionesShape9, portaavionesShape10, portaavionesShape11, portaavionesShape12, portaavionesShape13, portaavionesShape14);

        shapesGroup.setScaleX(1.1);
        shapesGroup.setScaleY(1.1);
        this.getChildren().add(shapesGroup);
        isSelect = false;
        size = 4;
    }


}