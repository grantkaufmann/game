package main.prefabs;

import javafx.scene.paint.Color;
import main.ID;
import main.gameengine.nodes.Player;
import main.gameengine.nodes.Sprite;

public class FlappyBG extends Sprite {

    public FlappyBG(int x, int y) {
    	setType("background");
        positionX = x;
        positionY = y;
    }

    public void initialize() {
        setImage("main/resources/flappybg.png");
        setVelocity(-2, 0);
        gc.drawImage( image, 0, -150 );
    }

    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {

    }


}
