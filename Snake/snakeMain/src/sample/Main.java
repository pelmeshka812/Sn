package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sample.Direction.*;

public class Main extends Application {
    Game game = new Game();
    GraphicsContext gc ;
    boolean lost = false;
    Thread thread;
    Canvas canvas;
    Direction direction;
    final int up = 1;
    final int right = 2;
    final int down = 3;
    final int left = 4;

    Snake snake = new Snake(game.getSize()/2, game.getSize()/2);
/*public void draw() {
    if(!lost) {
        Circle circle = new Circle();
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.rgb(255,0,0));
        circle.setFill(colorPicker.getValue());
        circle.setCenterX(x[0]);
        circle.setCenterY(y[0]);
        circle.setRadius(dot.getDot_size());
        //gc.fillOval(xSnake, ySnake, dot.getDot_size(), dot.getDot_size());
        for (int i = 0; i < length; i++) {
            colorPicker.setValue(Color.rgb(0, 0,255));
            circle.setFill(colorPicker.getValue());
            circle.setCenterX(x[i]);
            circle.setCenterY(y[i]);
            circle.setRadius(dot.getDot_size());

        }
    } else {
        gc.setFill(Paint.valueOf("black"));
        gc.fillText("Game Over", game.getSize()/ 2 - 50, game.getSize() / 2 - 15);
        thread.stop();
    }
}*/
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        canvas = new Canvas(game.getSize(), game.getSize());
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);
        startGame();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode key = e.getCode();
                if (key.equals(KeyCode.UP)) direction = UP;
                if (key.equals(KeyCode.DOWN)) direction = DOWN;
                if (key.equals(KeyCode.LEFT)) direction = LEFT;
                if (key.equals(KeyCode.RIGHT)) direction = RIGHT;
                snake.move(direction, gc);
            }

        });
        Scene scene = new Scene(root, game.getSize(), game.getSize());
        primaryStage.setTitle("Snake war");
        primaryStage.setScene(scene);
            primaryStage.show();


    }
    private void startGame(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(!lost){

                    }
                    snake.draw(gc);
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {

                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
