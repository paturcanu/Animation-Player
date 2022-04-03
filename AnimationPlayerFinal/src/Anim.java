/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
/**
 *
 * @author Phil's Predator
 */
public class Anim extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        
        
        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(125);
        circle.setStroke(Color.BLUE);
        circle.setStrokeWidth(5);
        circle.setFill(Color.TRANSPARENT);
        Rectangle rectangle = new Rectangle();
        rectangle.setX(200);
        rectangle.setY(200);
        rectangle.setWidth(300);
        rectangle.setHeight(400);
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setFill(Color.valueOf("#00ffff"));
        Pane pane = new Pane();
        pane.getChildren().add(circle);
        pane.getChildren().add(rectangle);
        Scene scene = new Scene(pane, 1024, 800, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2D Example");
        primaryStage.show();
        rectangle.setFill(Color.RED);
        Thread.sleep(2000);
        primaryStage.show();
        
    }

    
    /*public static void main(String[] args) {
        launch(args);
    }*/
    
}
