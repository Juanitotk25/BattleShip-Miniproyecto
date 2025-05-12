package com.example.miniproyecto3_battleship.model.ships;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

public class Destructor extends Ship {

    public Destructor() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture2.png")));
        ImagePattern imagePattern = new ImagePattern(image);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture5.png")));
        ImagePattern imagePattern2 = new ImagePattern(image2);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture1.png")));
        ImagePattern imagePattern5 = new ImagePattern(image5);
        Image image7 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata1.png")));
        ImagePattern imagePattern7 = new ImagePattern(image7);
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture2.png")));
        ImagePattern imagePattern3 = new ImagePattern(image3);

        Rectangle rectangle1 = new Rectangle(51, 30, 27, 78);
        rectangle1.setFill(Color.web("#565757"));
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeWidth(0);

        rectangle1.setFill(imagePattern);



        Line line1 = new Line(-13.5, -1.5, -13.5, 74.0);
        line1.setLayoutX(65.0);
        line1.setLayoutY(33.0);

        line1.setFill(imagePattern5);


        QuadCurve quadCurve1 = new QuadCurve(-19.728, -35.0, -6.478, -23.25, 6.772, -35.0);
        quadCurve1.setLayoutX(71.0);
        quadCurve1.setLayoutY(143.0);
        quadCurve1.setFill(Color.web("#575757"));
        quadCurve1.setStroke(Color.BLACK);

        quadCurve1.setFill(imagePattern3);


        Line line2 = new Line(-11.5, -3.0, -11.5, 72.0);
        line2.setLayoutX(89.0);
        line2.setLayoutY(35.0);
        line2.setFill(imagePattern5);


        QuadCurve quadCurve2 = new QuadCurve(-26.0, -19.0, -13.478, -71.75, 1.0, -19.0);
        quadCurve2.setLayoutX(77.0);
        quadCurve2.setLayoutY(51.0);
        quadCurve2.setFill(Color.web("#575757"));
        quadCurve2.setStroke(Color.BLACK);

        quadCurve2.setFill(imagePattern3);


        Rectangle rectangle2 = new Rectangle(56, 64, 17, 5);
        rectangle2.setArcWidth(5);
        rectangle2.setArcHeight(5);
        rectangle2.setStroke(Color.BLACK);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture6.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        rectangle2.setFill(imagePattern4);



        Line line3 = new Line(5.022, -13.0, 11.772, -13.0);
        line3.setLayoutX(56.0);
        line3.setLayoutY(47.0);
        line3.setFill(imagePattern5);


        Line line4 = new Line(-6.0, -27.25, -6.0, -14.0);
        line4.setLayoutX(77.0);
        line4.setLayoutY(67.0);
        line4.setFill(imagePattern5);


        Line line5 = new Line(-6.5, -26.25, -6.5, -13.5);
        line5.setLayoutX(64.0);
        line5.setLayoutY(66.0);
        line5.setFill(imagePattern5);


        Line line6 = new Line(-1.5, -22.0, 11.5, -22.0);
        line6.setLayoutX(59.0);
        line6.setLayoutY(75.0);
        line6.setFill(imagePattern5);


        Line line7 = new Line(6.272, -13.0, 9.0, -7.75);
        line7.setLayoutX(62.0);
        line7.setLayoutY(47.0);
        line7.setFill(imagePattern5);


        Line line8 = new Line(7.0, -13.0, 3.75, -8.0);
        line8.setLayoutX(54.0);
        line8.setLayoutY(47.0);
        line8.setFill(imagePattern5);


        Line line9 = new Line(-13.5, -1.5, -13.5, 74.0);
        line9.setLayoutX(68.0);
        line9.setLayoutY(33.0);
        line9.setFill(imagePattern5);


        Line line10 = new Line(-13.5, -1.5, -13.5, 74.0);
        line10.setLayoutX(88.0);
        line10.setLayoutY(33.0);
        line10.setStroke(Color.web("#0a0000"));
        line10.setFill(imagePattern5);


        Line line11 = new Line(-26.228, 64.5, -0.728, 64.5);
        line11.setLayoutX(78.0);
        line11.setLayoutY(43.0);


        Line line12 = new Line(-26.228, 64.5, -0.478, 64.5);
        line12.setLayoutX(78.0);
        line12.setLayoutY(-33.0);


        Rectangle rectangle3 = new Rectangle(57, 21, 14, 4);
        rectangle3.setArcWidth(5);
        rectangle3.setFill(Color.web("#00030500"));
        rectangle3.setStroke(Color.BLACK);
        rectangle3.setFill(imagePattern5);


        Rectangle rectangle4 = new Rectangle(56, 81, 17, 14);
        rectangle4.setArcWidth(5);
        rectangle4.setFill(Color.web("#00030500"));
        rectangle4.setStroke(Color.BLACK);
        rectangle4.setFill(imagePattern5);


        Rectangle rectangle5 = new Rectangle(57, 15, 4, 10);
        rectangle5.setArcWidth(5);
        rectangle5.setStroke(Color.BLACK);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture4.png")));
        ImagePattern imagePattern6 = new ImagePattern(image6);
        rectangle5.setFill(imagePattern6);


        Rectangle rectangle6 = new Rectangle(67, 15, 4, 10);
        rectangle6.setArcWidth(5);
        rectangle6.setStroke(Color.BLACK);
        rectangle6.setFill(imagePattern6);

        Rectangle rectangle7 = new Rectangle(76, 53, 4, 10);
        rectangle7.setArcWidth(5);
        rectangle7.setRotate(90);
        rectangle7.setStroke(Color.BLACK);
        rectangle7.setFill(imagePattern5);

        Rectangle rectangle8 = new Rectangle(49, 53, 4, 10);
        rectangle8.setArcWidth(5);
        rectangle8.setRotate(90);
        rectangle8.setStroke(Color.BLACK);
        rectangle8.setFill(imagePattern5);

        Rectangle rectangle9 = new Rectangle(49, 69, 4, 10);
        rectangle9.setArcWidth(5);
        rectangle9.setRotate(90);
        rectangle9.setStroke(Color.BLACK);
        rectangle9.setFill(imagePattern5);

        Rectangle rectangle10 = new Rectangle(76, 69, 4, 10);
        rectangle10.setArcWidth(5);
        rectangle10.setRotate(90);
        rectangle10.setStroke(Color.BLACK);
        rectangle10.setFill(imagePattern5);

        Rectangle rectangle11 = new Rectangle(60, 84, 8.5, 8.5);
        rectangle11.setArcWidth(5);
        rectangle11.setFill(Color.web("#474d51"));
        rectangle11.setStroke(Color.BLACK);

        Rectangle rectangle12 = new Rectangle(58, 48, 12.5, 4);
        rectangle12.setArcWidth(5);
        rectangle12.setFill(Color.web("#00030500"));
        rectangle12.setStroke(Color.BLACK);
        rectangle12.setStrokeWidth(0.5);

        Rectangle rectangle13 = new Rectangle(67, 105, 4, 10);
        rectangle13.setArcWidth(5);
        rectangle13.setStroke(Color.BLACK);
        rectangle13.setFill(imagePattern5);
        Rectangle rectangle14 = new Rectangle(58, 105, 4, 10);

        rectangle14.setArcWidth(5);
        rectangle14.setStroke(Color.BLACK);
        rectangle14.setFill(imagePattern5);

        Line line13 = new Line(-13.0, 5.0, 0, 5.0);
        line13.setLayoutX(71.0);
        line13.setLayoutY(100.0);
        line13.setStroke(Color.web("#a8a8a8"));

        Line line14 = new Line(-24.978, -83.0, -9.0, -83.0);
        line14.setLayoutX(81.0);
        line14.setLayoutY(109.0);
        line14.setStroke(Color.web("#a8a8a8"));


        shapesGroup.setRotate(90);
        shapesGroup.setLayoutX(0);
        shapesGroup.setLayoutY(-25);


        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.3);
        shapesGroup.setEffect(glow);
        shapesGroup.setStyle("-fx-cursor: hand");

        shapesGroup.setScaleX(1.1);
        shapesGroup.setScaleY(1.1);
        shapesGroup.getChildren().addAll(rectangle1, line1, quadCurve1, line2, quadCurve2, rectangle2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, rectangle3, rectangle4, rectangle5, rectangle6, rectangle7, rectangle8, rectangle9, rectangle10, rectangle11, rectangle12, rectangle13, rectangle14);
        this.getChildren().add(shapesGroup);
        isSelect = false;
        size = 2;
    }


}

