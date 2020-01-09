package main;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.gameengine.nodes.Sprite;

import java.util.Random;

public class Ball extends Sprite {

    public Ball(double radius, Color fill, int x, int y) {
        setCanvasSize(radius * 2, radius * 2);
        positionX = x;
        positionY = y;
        gc.setFill(fill);
        gc.fillRect(0, 0, radius * 2, radius * 2);
    }

    public void initialize() {
        setID(ID.Ball);
//        setVelocity(1, 1);
    }

    public void handleUpdate() {
        updatePosition();
        collidesWall();
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {
        System.out.println(id + " collided with2 " + spriteB.id);

        setVelocity(velocityX * -1, velocityY * -1);

        // spriteB.velocityX = spriteB.velocityX * -1;
        // spriteB.velocityY = spriteB.velocityY * -1;
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
