package Server.prefabs;

import Server.nodes.Sprite;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ball extends Sprite {

    public Ball(int x, int y) {
    	setType(this.getClass().getSimpleName().toLowerCase());
        setCanvasSize(20, 20);
        positionX = x;
        positionY = y;

        setVelocity(2, 2);
    }

    public void handleUpdate() {
        updatePosition();
        collidesWall();
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {
        // System.out.println(getType() + " of " + node.getScene() + " collided with2 " + spriteB.getType() + " of " + node.getScene());

        // setVelocity(velocityX * -1, velocityY * -1);

        // spriteB.velocityX = spriteB.velocityX * -1;
        // spriteB.velocityY = spriteB.velocityY * -1;
    }
}
