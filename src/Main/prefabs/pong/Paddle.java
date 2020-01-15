package Main.prefabs.pong;

import JGame.JGame;
import JGame.nodes.Sprite;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Paddle extends Sprite {

    private int speed = 5;

    public Paddle(double x, double y) {
        setType("paddle");
        setPosition(x, y);
        setCanvasSize(10, 80);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 10, 80);
    }

    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);

        if (JGame.keyPressManager.isKeyPressed(KeyCode.W)) 			{ velocityY = -speed;}
        else if (JGame.keyPressManager.isKeyPressed(KeyCode.S))		{ velocityY = speed;}
        else 																		{ velocityY = 0;}
    }

    public void handleRender() {
        // System.out.println(JGame.getSceneNodes().getChildren().size());
    }
}
