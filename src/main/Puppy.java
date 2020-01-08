package main;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.gameengine.Sprite;

public class Puppy extends Sprite {

    int speed = 5;

    public Puppy(int x, int y) {
        // this.id = id;
        positionX = x;
        positionY = y;
    }

    public void initialize() {
        setID(ID.Puppy);
        setImage("main/resources/puppy.png");

        gc.drawImage( image, 0, 0 );
    }

    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);


        collidesWall();
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {
        System.out.println(id + " collided with: " + spriteB.id);
        velocityX = velocityX * -1;
        velocityY = velocityY * -1;

        spriteB.velocityX = spriteB.velocityX * -1;
        spriteB.velocityY = spriteB.velocityY * -1;
    }

    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {
        // System.out.println("You pressed: " + keyCode);
        if(keyCode == KeyCode.W && isPressed) { velocityY = -speed; }
        if(keyCode == KeyCode.W && !isPressed) { velocityY = 0; }

        if(keyCode == KeyCode.A && isPressed) { velocityX = -speed; }
        if(keyCode == KeyCode.A && !isPressed) { velocityX = 0; }

        if(keyCode == KeyCode.S && isPressed) { velocityY = speed; }
        if(keyCode == KeyCode.S && !isPressed) { velocityY = 0; }

        if(keyCode == KeyCode.D && isPressed) { velocityX = speed; }
        if(keyCode == KeyCode.D && !isPressed) { velocityX = 0; }
    }
}
