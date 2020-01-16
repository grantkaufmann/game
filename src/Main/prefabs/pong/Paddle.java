package Main.prefabs.pong;

import JGame.JGame;
import JGame.nodes.Sprite;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Paddle extends Sprite {

    private int speed = 5;

    public Paddle(double x, double y, String uuid) {
        setType("paddle");
        setUuid(uuid);
        setPosition(x, y);
        setCanvasSize(10, 80);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10, 80);
    }

    public void handleRender() {

    }
}
