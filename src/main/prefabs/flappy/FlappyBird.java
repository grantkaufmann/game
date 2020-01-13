package main.prefabs.flappy;

import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import main.gameengine.KeyboardManager;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.gameengine.nodes.Sprite;

import java.io.File;

public class FlappyBird extends Sprite {

    private boolean alive = true;
    private SpriteManager spriteManager;
    private SceneManager sceneManager;
    private KeyboardManager keyPressManager;

    private int terminalVelocity = 8;

    public FlappyBird(int x, int y, SpriteManager spriteManager, SceneManager sceneManager, KeyboardManager keyPressManager) {
        this.spriteManager = spriteManager;
        this.sceneManager = sceneManager;
        this.keyPressManager = keyPressManager;
    	setType("bird");
        setPosition(x, y);
    }

    public void initialize() {
        setImage("main/resources/flappy/flappybird.png", 0.1);
        addSound("flap", "main/resources/flappy/flappyflap.mp3");
        gc.drawImage( image, 0, 0 );
        setInitial();
    }

    public void handleUpdate() {
       if (node.getTranslateY() + 100 > node.getScene().getHeight()) {
           handleDie();
           velocityY = 0;
       }

        if (node.getTranslateY() < 0) {
            handleDie();
        }

       if (alive) {
           node.setRotate(node.getRotate() + 2);
           if (node.getRotate() > 90.0) {
               node.setRotate(90.0);
           }

           velocityY = velocityY + 0.5;
           if (velocityY > terminalVelocity) {
               velocityY = terminalVelocity;
           }
           if (velocityY < -terminalVelocity) {
               velocityY = -terminalVelocity;
           }
           node.setTranslateY(positionY += velocityY);
       } else {
           node.setTranslateY(positionY -= velocityY);
       }


        if (keyPressManager.isKeyPressed(KeyCode.R)) {
            reset();
            alive = true;
            for (int i = 0; i < spriteManager.getSpriteByType("background").size(); i++) {
                spriteManager.getSpriteByType("background").get(i).reset();
            }
            for (int i = 0; i < spriteManager.getSpriteByType("pipe").size(); i++) {
                spriteManager.getSpriteByType("pipe").get(i).reset();
            }
        }
    }

    public void handleRender() {

    }

    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {
        if (keyCode.getChar().equals(" ") && isPressed) {
            playSound("flap");
            setVelocity(0, velocityY - terminalVelocity * 2);
            setRotation(-90);
        }
    }

    public void handleCollisions(Sprite spriteB) {
        if (spriteB.isType("pipe")) {
            handleDie();
        }
    }

    public void handleDie() {
        alive = false;
        velocityY = -8;
        for (int i = 0; i < spriteManager.getSpriteByType("background").size(); i++) {
            spriteManager.getSpriteByType("background").get(i).velocityX = 0;
        }
        for (int i = 0; i < spriteManager.getSpriteByType("pipe").size(); i++) {
            spriteManager.getSpriteByType("pipe").get(i).velocityX = 0;
        }
        setRotation(180);
    }


}
