package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.gameengine.Sprite;

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
}
