package main;

import main.gameengine.Sprite;

public class Puppy extends Sprite {
    public void initialize() {
        System.out.println("I HAVE BEEN INITIALIZED");
        setImage("main/resources/puppy.png");
        setVelocity(2, 2);

        System.out.println("image: " + image);
        System.out.println("X: " + positionX);
        System.out.println("Y: " + positionY);
        gc.drawImage( image, positionX, positionY );
    }

    public void handleUpdate() {
        // System.out.println("I HAVE BEEN INITIALIZED");
        System.out.println("I should be updating");
//        sprite.update(1);
//        if (sprite.node.getTranslateX() > (getGameSurface().getWidth()  -
//                sprite.node.getBoundsInParent().getWidth()) ||
//                sprite.node.getTranslateX() < 0 ) {
//            sprite.velocityX = sprite.velocityX * -1;
//        }
//        if (sprite.node.getTranslateY() > getGameSurface().getHeight()-
//                sprite.node.getBoundsInParent().getHeight() ||
//                sprite.node.getTranslateY() < 0) {
//            sprite.velocityY = sprite.velocityY * -1;
//        }
    }

    public void handleRender() {

    }
}
