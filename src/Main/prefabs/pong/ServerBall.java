package Main.prefabs.pong;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import JGame.nodes.Sprite;

import java.util.Random;

public class ServerBall extends Sprite {

    public ServerBall(double radius, int x, int y) {
        setType("ball");
        setCanvasSize(radius * 2, radius * 2);
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
}
