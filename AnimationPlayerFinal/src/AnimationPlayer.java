
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
import java.lang.String;
import javafx.scene.Node;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Phil's Predator
 */
public class AnimationPlayer extends Application {
    
    public static void main(String[] args){
        launch(args);
    
        
    }
    
    public int frames, frame;
    public int speed, delayInMs;
    public int elements;
    public Pane pane = new Pane();
    public String line;
    public String fileName = "C:\\Users\\Phil's Predator\\Documents\\NetBeansProjects\\Animation-Player\\AnimationPlayerFinal\\src\\animation.txt";
    
    private String[] splitLine;
    
    public void run(Stage primaryStage) throws InterruptedException {
        Scene scene = new Scene(pane, 1024, 800, true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2D Example");
        for (int i = 0; i < frames; i++) { 
            //Attempt to do the animation
            long initialTime = System.currentTimeMillis() ;
            System.out.println(initialTime);
            while(true){
                long deltaTime = System.currentTimeMillis() - initialTime;
                if (deltaTime >= 10) {
                    break;
                }
            }
            for(Node node: pane.getChildren()) {
                if( node instanceof CircleEffects) {
                    CircleEffects cir = (CircleEffects) node;
                    cir.ApplyEffect(i);
                }
                if( node instanceof RectangleEffects) {
                    RectangleEffects rec = (RectangleEffects) node;
                    rec.ApplyEffect(i);
                }
                if( node instanceof LineEffects) {
                    LineEffects lin = (LineEffects) node;
                    lin.ApplyEffect(i);
                }

            }
            primaryStage.show();
        }
        
        
    }
    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        loadAnimationFromFile(fileName);
        run(primaryStage);
    }
    public void loadAnimationFromFile(String fileName) throws IOException {    
        BufferedReader input = null;
        try {
            input = new BufferedReader(
                new FileReader(
                    new File(fileName)));
            while (true) {
                //System.out.println("Entering loop");
                line = input.readLine();
                //System.out.println("Current line: " + line);
                if (line == null) {
                    //System.out.println("Line is null");
                    break;
                }
                if (line.contains("frames")) {
                    splitLine = line.split(": ", 2);
                    frames = Integer.parseInt(splitLine[1]);
                    //System.out.println("frames: " + frames);
                }
                if (line.contains("speed")) {
                    splitLine = line.split(" ", 3);
                    speed = Integer.parseInt(splitLine[1]);
                    delayInMs = 1000/speed;
                    System.out.println("speed: " + delayInMs);
                }
                String pattern = "[0-9]{1,}";
                if (line.matches(pattern)) {
                    elements = Integer.parseInt(line);
                    //System.out.println("elements: " + elements);
                }
                if (line.contains("Circle")) {
                    CircleEffects circle = new CircleEffects();
                    circle.myEffects.addEffect(0, EffectType.HIDE);
                    while (line.compareTo("") != 0) {
                        line = input.readLine();

                        if (line.contains("Color: ")) {
                            //System.out.println("Color ");
                            splitLine = line.split("Color: ", 2);
                            splitLine = splitLine[1].split(", ", 3);
                            int R = Integer.parseInt(splitLine[0]);
                            int G = Integer.parseInt(splitLine[1]);
                            int B = Integer.parseInt(splitLine[2]);
                            circle.setFill(Color.rgb(R, G, B));
                            continue;
                        }
                        if (line.contains("r: ")) {
                            //System.out.println("Radius ");
                            splitLine = line.split("r: ", 2);
                            circle.setRadius(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("x: ")) {
                            //System.out.println("X ");
                            splitLine = line.split("x: ", 2);
                            circle.setCenterX(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("y: ")) {
                            //System.out.println("Y ");
                            splitLine = line.split("y: ", 2);
                            circle.setCenterY(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("border: ")) {
                            //System.out.println("Border ");
                            splitLine = line.split("border: ", 2);
                            circle.setStrokeWidth(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        
                        if (line.contains("effect")) {
                            while (line.compareTo("") != 0) {
                                line = input.readLine();
                                if (line.contains("Hide")) {
                                    //System.out.println("Hide ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        circle.myEffects.addEffect(frame, EffectType.HIDE);
                                    }
                                    continue;
                                }
                                if (line.contains("Show")) {
                                    //System.out.println("Show ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        circle.myEffects.addEffect(frame, EffectType.SHOW);
                                    }
                                    continue;
                                }
                                if (line.contains("Jump")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        circle.myEffects.addEffect(frame, EffectType.JUMP);
                                        line = input.readLine();
                                        if (line.contains("x: ")) {
                                            splitLine = line.split("x: ", 2);
                                            circle.jumpX = Integer.parseInt(splitLine[1]);
                                            line = input.readLine();
                                            if (line.contains("y: ")) {
                                                splitLine = line.split("y: ", 2);
                                                circle.jumpY = Integer.parseInt(splitLine[1]);
                                            }
                                        }
                                    }
                                    continue;
                                }
                                if (line.contains("ChangeColor")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        circle.myEffects.addEffect(frame, EffectType.CHANGECOLOR);
                                        line = input.readLine();
                                        if (line.contains("color: ")) {
                                            splitLine = line.split("Color: ", 2);
                                            splitLine = splitLine[1].split(", ", 3);
                                            int R = Integer.parseInt(splitLine[0]);
                                            int G = Integer.parseInt(splitLine[1]);
                                            int B = Integer.parseInt(splitLine[2]);
                                            circle.newColor = Color.rgb(R, G, B);
                                            
                                        }
                                    }
                            
                                }
                            }
                        
                        }
                        
                    }
                    pane.getChildren().add(circle);
                    
                }
                if (line.contains("Rect")) {
                    RectangleEffects rectangle = new RectangleEffects();
                    while (line.compareTo("") != 0) {
                        line = input.readLine();

                        if (line.contains("Color: ")) {
                            //System.out.println("Color ");
                            splitLine = line.split("Color: ", 2);
                            splitLine = splitLine[1].split(", ", 3);
                            int R = Integer.parseInt(splitLine[0]);
                            int G = Integer.parseInt(splitLine[1]);
                            int B = Integer.parseInt(splitLine[2]);
                            rectangle.setFill(Color.rgb(R, G, B));
                            continue;
                        }
                        if (line.contains("length: ")) {
                            //System.out.println("length ");
                            splitLine = line.split("length: ", 2);
                            rectangle.setHeight(Integer.parseInt(splitLine[1]));
                            continue;
                            
                        }
                        if (line.contains("width: ")) {
                            //System.out.println("Width ");
                            splitLine = line.split("width: ", 2);
                            rectangle.setWidth(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("x: ")) {
                            //System.out.println("X ");
                            splitLine = line.split("x: ", 2);
                            rectangle.setX(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("y: ")) {
                            //System.out.println("Y ");
                            splitLine = line.split("y: ", 2);
                            rectangle.setY(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("border: ")) {
                            //System.out.println("Border ");
                            splitLine = line.split("border: ", 2);
                            rectangle.setStrokeWidth(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("effect")) {
                            while (line.compareTo("") != 0) {
                                line = input.readLine();
                                if (line.contains("Hide")) {
                                    //System.out.println("Hide ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        rectangle.myEffects.addEffect(frame, EffectType.HIDE);
                                    }
                                    continue;
                                }
                                if (line.contains("Show")) {
                                    //System.out.println("Show ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        rectangle.myEffects.addEffect(frame, EffectType.SHOW);
                                    }
                                    continue;
                                }
                                if (line.contains("Jump")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        rectangle.myEffects.addEffect(frame, EffectType.JUMP);
                                        line = input.readLine();
                                        if (line.contains("x: ")) {
                                            splitLine = line.split("x: ", 2);
                                            rectangle.jumpX = Integer.parseInt(splitLine[1]);
                                            line = input.readLine();
                                            if (line.contains("y: ")) {
                                                splitLine = line.split("y: ", 2);
                                                rectangle.jumpY = Integer.parseInt(splitLine[1]);
                                            }
                                        }
                                    }
                                    continue;
                                }
                                if (line.contains("ChangeColor")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        rectangle.myEffects.addEffect(frame, EffectType.CHANGECOLOR);
                                        line = input.readLine();
                                        if (line.contains("color: ")) {
                                            splitLine = line.split("Color: ", 2);
                                            splitLine = splitLine[1].split(", ", 3);
                                            int R = Integer.parseInt(splitLine[0]);
                                            int G = Integer.parseInt(splitLine[1]);
                                            int B = Integer.parseInt(splitLine[2]);
                                            rectangle.newColor = Color.rgb(R, G, B);
                                            
                                        }
                                    }
                            
                                }
                            }
                        
                        }
                        
                    }
                    pane.getChildren().add(rectangle);
                    
                
                }
                if (line.contains("Line")) {
                    LineEffects l = new LineEffects();
                    while (line.compareTo("") != 0) {
                        line = input.readLine();

                        if (line.contains("Color: ")) {
                            //System.out.println("Color ");
                            splitLine = line.split("Color: ", 2);
                            splitLine = splitLine[1].split(", ", 3);
                            int R = Integer.parseInt(splitLine[0]);
                            int G = Integer.parseInt(splitLine[1]);
                            int B = Integer.parseInt(splitLine[2]);
                            l.setStroke(Color.rgb(R, G, B));
                            continue;
                        }
                        if (line.contains("startX: ")) {
                            //System.out.println("startX ");
                            splitLine = line.split("startX: ", 2);
                            l.setStartX(Integer.parseInt(splitLine[1]));
                            continue;
                            
                        }
                        if (line.contains("startY: ")) {
                            //System.out.println("startY ");
                            splitLine = line.split("startY: ", 2);
                            l.setStartY(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("endX: ")) {
                            //System.out.println("endX ");
                            splitLine = line.split("endX: ", 2);
                            l.setEndX(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("endY: ")) {
                            //System.out.println("endY ");
                            splitLine = line.split("endY: ", 2);
                            l.setEndY(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("border: ")) {
                            //System.out.println("Border ");
                            splitLine = line.split("border: ", 2);
                            l.setStrokeWidth(Integer.parseInt(splitLine[1]));
                            continue;
                        }
                        if (line.contains("effect")) {
                            while (line.compareTo("") != 0) {
                                line = input.readLine();
                                if (line.contains("Hide")) {
                                    //System.out.println("Hide ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        l.myEffects.addEffect(frame, EffectType.HIDE);
                                    }
                                    continue;
                                }
                                if (line.contains("Show")) {
                                    //System.out.println("Show ");
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        l.myEffects.addEffect(frame, EffectType.SHOW);
                                    }
                                    continue;
                                }
                                if (line.contains("Jump")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        l.myEffects.addEffect(frame, EffectType.JUMP);
                                        line = input.readLine();
                                        if (line.contains("x: ")) {
                                            splitLine = line.split("x: ", 2);
                                            l.jumpX = Integer.parseInt(splitLine[1]);
                                            line = input.readLine();
                                            if (line.contains("y: ")) {
                                                splitLine = line.split("y: ", 2);
                                                l.jumpY = Integer.parseInt(splitLine[1]);
                                            }
                                        }
                                    }
                                    continue;
                                }
                                if (line.contains("ChangeColor")) {
                                    line = input.readLine();
                                    if (line.contains("start: ")) {
                                        splitLine = line.split("start: ", 2);
                                        frame = Integer.parseInt(splitLine[1]);
                                        l.myEffects.addEffect(frame, EffectType.CHANGECOLOR);
                                        line = input.readLine();
                                        if (line.contains("color: ")) {
                                            splitLine = line.split("Color: ", 2);
                                            splitLine = splitLine[1].split(", ", 3);
                                            int R = Integer.parseInt(splitLine[0]);
                                            int G = Integer.parseInt(splitLine[1]);
                                            int B = Integer.parseInt(splitLine[2]);
                                            l.newColor = Color.rgb(R, G, B);
                                        }
                                    }
                            
                                }
                            }
                        
                        }
                        
                    }
                    pane.getChildren().add(l);
                    
                
                }
                
                
            }
          
            

                
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(AnimationPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
