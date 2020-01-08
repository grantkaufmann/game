package main;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.gameengine.Sprite;

import java.util.Random;

public class Ball extends Sprite {
    public Ball(double radius, Color fill) {
        setCanvasSize(radius * 2, radius * 2);
        gc.setFill(fill);
        gc.fillOval(0, 0, radius * 2, radius * 2);
    }

    public void initialize() {
        setID(ID.Ball);
        setVelocity(1, 1);
    }

    public void handleUpdate() {
        updatePosition();
        collidesWall();
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {

    }

    public void handleMouseEvent(MouseEvent mouseEvent, boolean isPressed) {
        if (isPressed) {
            System.out.println("Mouse Button: " + mouseEvent.getButton());
            System.out.println("Mouse Position: " + mouseEvent.getX() + " - " + mouseEvent.getY());

            if (intersects(mouseEvent.getX(), mouseEvent.getY())) {
                Random rand = new Random();
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                gc.setFill(Color.rgb(r, g, b));
                gc.fillOval(0, 0, width, height);
            }
        }
    }
}
