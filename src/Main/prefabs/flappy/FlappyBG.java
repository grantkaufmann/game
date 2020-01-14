package Main.prefabs.flappy;

import JGame.nodes.Sprite;

public class FlappyBG extends Sprite {

    public FlappyBG(int x, int y) {
    	setType("background");
        positionX = x;
        positionY = y;
    }

    public void initialize() {
        setImage("Main/resources/flappy/flappybg.png");
        setVelocity(-2, 0);
        gc.drawImage( image, 0, -150 );
        setInitial();
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
