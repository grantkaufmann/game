package main.prefabs;

import javafx.scene.input.KeyCode;
import main.gameengine.SpriteManager;
import main.gameengine.nodes.Sprite;

public class FlappyPipe extends Sprite {

    public FlappyPipe(int x, int y, double rot) {
    	setType("pipe");
        positionX = x;
        positionY = y;
        setRotation(rot);
    }

    public void initialize() {
        setImage("main/resources/flappypipe.png", 0.7);
        setVelocity(-2, 0);
        gc.drawImage( image, 0, 0 );
    }

    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {

    }

    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {

    }
}
