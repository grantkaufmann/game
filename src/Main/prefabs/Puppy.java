package main.prefabs;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.gameengine.nodes.Sprite;
import main.gameengine.nodes.Player;
import main.ID;

public class Puppy extends Player {

//    int speed = 5;
	
    public Puppy(int x, int y) {
        // this.id = id;
    	setType(this.getClass().getSimpleName().toLowerCase());
        positionX = x;
        positionY = y;
        setSpeed(5);
        
//        System.out.println("Player" + getType());
//        System.out.println("Sprite?" + isType("Sprite"));
//        System.out.println("Player?" + isType("Player"));
//        System.out.println("Puppy?" + isType("Puppy"));
    }

    public void initialize() {
        setID(ID.Puppy);
        setImage("main/resources/puppy.png");
        gc.drawImage( image, 0, 0 );
    }

    public void handleUpdate() {
    	super.handleUpdate();
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.SPACE)) {
      		Arrow arrow = new Arrow(10, Color.RED, node.getTranslateX() + (width / 2), node.getTranslateY(), 0, -15);
      		main.GrantGame.getSceneNodes().getChildren().add(arrow.getNode());
      		main.GrantGame.spriteManager.addSprites(arrow);
        }
        collidesWall();
    }

    public void handleRender() {

    }

    public void handleCollisions(Sprite spriteB) {
//        System.out.println(id + " collided with: " + spriteB.id);
//        velocityX = velocityX * -1;
//        velocityY = velocityY * -1;
//        spriteB.velocityX = spriteB.velocityX * -1;
//        spriteB.velocityY = spriteB.velocityY * -1;
    }


}
