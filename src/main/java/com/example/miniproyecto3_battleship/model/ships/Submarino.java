package com.example.miniproyecto3_battleship.model.ships;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;

import java.util.Collection;
import java.util.Objects;

public class Submarino extends Ship {

    public Submarino() {
        this.setWidth(190);
        this.setHeight(63);
        Image image2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture2.png")));
        ImagePattern imagePattern2 = new ImagePattern(image2);
        Image image3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture3.png")));
        ImagePattern imagePattern3 = new ImagePattern(image3);
        Image image4 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wowtextureFragata3.png")));
        ImagePattern imagePattern4 = new ImagePattern(image4);
        Image image5 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture5.png")));
        ImagePattern imagePattern5 = new ImagePattern(image5);
        Image image6 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/miniproyecto3_battleship/Image/wow_texture6.png")));
        ImagePattern imagePattern6 = new ImagePattern(image6);
        size =  3;
        Ellipse ellipse = new Ellipse(91.0, 29.0, 63.0, 22.0);
        ellipse.setFill(Color.web("#57595b"));
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(1.5);
        ellipse.setFill(imagePattern2);

        // Rectangle 1
        Rectangle rectangle1 = new Rectangle(23.0, 17.0, 11.0, 24.0);
        rectangle1.setFill(Color.web("#444e57"));
        rectangle1.setStroke(Color.BLACK);
        rectangle1.setStrokeWidth(1.0);
        rectangle1.setFill(imagePattern2);
        // Polygon 1
        Polygon polygon1 = new Polygon(-13.0, -25.0, -5.25, -18.75, -13.0, -13.5);
        polygon1.setLayoutX(47.0);
        polygon1.setLayoutY(34.0);
        polygon1.setFill(Color.web("#292b2c"));
        polygon1.setStroke(Color.BLACK);
        polygon1.setStrokeWidth(0.0);

        // Rectangle 2
        Rectangle rectangle2 = new Rectangle(22.0, 9.0, 12.0, 5.0);
        rectangle2.setFill(Color.web("#4c5257"));
        rectangle2.setStroke(Color.BLACK);
        rectangle2.setStrokeWidth(0.0);
        rectangle2.setFill(imagePattern4);
        // Polygon 2
        Polygon polygon2 = new Polygon(-10.0, -25.75, -1.5, -20.0, -10.0, -15.0);
        polygon2.setLayoutX(44.0);
        polygon2.setLayoutY(63.0);
        polygon2.setFill(Color.web("#292b2c"));
        polygon2.setStroke(Color.BLACK);
        polygon2.setStrokeWidth(0.0);

        // Rectangle 3
        Rectangle rectangle3 = new Rectangle(22.0, 43.0, 12.0, 5.0);
        rectangle3.setFill(Color.web("#4c5257"));
        rectangle3.setStroke(Color.BLACK);
        rectangle3.setStrokeWidth(0.0);
        rectangle3.setFill(imagePattern4);

        // Circle
        Circle circle = new Circle(91.0, 29.0, 9.0);
        circle.setFill(Color.web("#5a6168"));
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(0.0);
        circle.setFill(imagePattern3);

        // Line 1
        Line line1 = new Line(90.0, 29.0, 34.0, 29.0);
        line1.setStroke(Color.web("#9e8707"));

        // Line 2
        Line line2 = new Line(150.0, 29.0, 100.25, 29.0);
        line2.setStroke(Color.web("#9e8707"));

        // Rectangle 4
        Rectangle rectangle4 = new Rectangle(78.0, 5.0, 24.0, 4.0);
        rectangle4.setFill(Color.web("#57595b"));
        rectangle4.setStroke(Color.BLACK);
        rectangle4.setStrokeWidth(0.0);
        rectangle4.setFill(imagePattern4);
        // Rectangle 5
        Rectangle rectangle5 = new Rectangle(79.0, 49.0, 24.0, 4.0);
        rectangle5.setFill(Color.web("#57595b"));
        rectangle5.setStroke(Color.BLACK);
        rectangle5.setStrokeWidth(0.0);
        rectangle5.setFill(imagePattern4);
        // QuadCurve 1
        QuadCurve quadCurve1 = new QuadCurve(-1.0, -28.5, -16.5, -33.5, -1.0, -38.25);
        quadCurve1.setLayoutX(148.0);
        quadCurve1.setLayoutY(49.0);
        quadCurve1.setFill(Color.web("#373a3c"));
        quadCurve1.setStroke(Color.BLACK);
        quadCurve1.setStrokeWidth(1.0);
        quadCurve1.setFill(imagePattern5);
        // QuadCurve 2
        QuadCurve quadCurve2 = new QuadCurve(-1.0, -28.5, -16.5, -33.5, -1.0, -38.25);
        quadCurve2.setLayoutX(148.0);
        quadCurve2.setLayoutY(75.0);
        quadCurve2.setFill(Color.web("#373a3c"));
        quadCurve2.setStroke(Color.BLACK);
        quadCurve2.setStrokeWidth(1.0);
        quadCurve2.setFill(imagePattern5);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.CADETBLUE);
        glow.setRadius(10);
        glow.setSpread(0.3);
        shapesGroup.setEffect(glow);
        shapesGroup.setStyle("-fx-cursor: hand");
        shapesGroup.setLayoutX(10);
        shapesGroup.setLayoutY(2);
        shapesGroup.setScaleX(0.9);
        shapesGroup.setScaleY(0.9);

        shapesGroup.getChildren().addAll(ellipse, rectangle1, polygon1, rectangle2, polygon2, rectangle3,
                circle, line1, line2, rectangle4, rectangle5, quadCurve1, quadCurve2);

        shapesGroup.setScaleX(0.9);
        shapesGroup.setScaleY(0.9);
        this.getChildren().add(shapesGroup);
        isSelect = false;
        size = 3;
    }
}
