package main;

import javafx.scene.paint.Color;
import main.gameengine.Sprite;

public class Puppy extends Sprite {

    public Puppy(int x, int y) {
        // this.id = id;
        positionX = x;
        positionY = y;
    }

    public void initialize() {
        setID(ID.Puppy);
        setImage("main/resources/puppy.png");
        setVelocity(2, 2);

        gc.drawImage( image, 0, 0 );
    }

    public void handleUpdate() {
        updatePosition();

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
}
