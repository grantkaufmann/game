package Main.prefabs.pong;

import JGame.JGame;
import JGame.nodes.Sprite;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends Sprite {

    private int speed = 5;

    public Player(double x, double y, String uuid) {
        setType("player");
        setUuid(uuid);
        setPosition(x, y);
        setCanvasSize(10, 80);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10, 80);
    }

    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);

        if (JGame.keyPressManager.isKeyPressed(KeyCode.W)) {
            // JGame.clientManager.submit("ball", 100, positionY, "454353535345345345345");
            JGame.currentLevel.createSprite("ball", 100, positionY, "345345345345345345");
            velocityY = -speed;
        }
        else if (JGame.keyPressManager.isKeyPressed(KeyCode.S)) {
            JGame.clientManager.submit("ball", 100, positionY, "454353535345345345345");
            velocityY = speed;}
        else 																		{
            velocityY = 0;
        }
    }

    public void handleRender() {
        // System.out.println(JGame.getSceneNodes().getChildren().size());
    }
}
