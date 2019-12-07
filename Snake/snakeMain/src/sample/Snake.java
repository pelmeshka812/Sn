package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private int length = 3;
    List<Dot> dots = new LinkedList<>();

    Direction direction = Direction.RIGHT;
  


    public Snake(int x, int y) {
        for (int i = 0; i < length; i++) {
            x += Dot.DOT_SIZE ;
            dots.add(new Dot(x, y));
        }
    }


    public void move(Direction direction, GraphicsContext gc ) {
        this.direction = direction;
        clear(gc);
        for (int i = length - 1; i > 0; i--) {
            dots.set(i, dots.get(i - 1));
            //gc.clearRect(dots.get(i).getX(),dots.get(i).getY(), Dot.DOT_SIZE, Dot.DOT_SIZE);
        }
        Dot dot = dots.get(0);
        if (direction == Direction.UP) dot.setY(dot.getY() - Dot.DOT_SIZE);
        if (direction == Direction.DOWN)dot.setY(dot.getY() + Dot.DOT_SIZE) ;
        if (direction == Direction.RIGHT) dot.setX(dot.getX() + Dot.DOT_SIZE);
        if (direction == Direction.LEFT) dot.setX(dot.getX() - Dot.DOT_SIZE);
        draw(gc);

    }
    public void grow(GraphicsContext gc){
        clear(gc);
        length++;
        draw(gc);
    }
    public void draw(GraphicsContext gc){
        gc.setFill(Paint.valueOf("blue"));
        gc.fillOval(dots.get(0).getX(), dots.get(0).getY(), Dot.DOT_SIZE,Dot.DOT_SIZE);
        for (int i = 1; i < length; i++) {
            gc.setFill(Paint.valueOf("yellow"));
            gc.fillOval(dots.get(i).getX(), dots.get(i).getY(), Dot.DOT_SIZE, Dot.DOT_SIZE);
        }
    }

    private void clear(GraphicsContext gc) {
        for (Dot dot : dots) {
            gc.clearRect(dot.getX(), dot.getY(), Dot.DOT_SIZE, Dot.DOT_SIZE);
        }
    }


}
