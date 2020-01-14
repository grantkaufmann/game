package main.prefabs;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import main.ID;
import main.gameengine.nodes.Item;
import main.gameengine.nodes.Sprite;

public class Arrow extends Item {
	public int damage = 1;
	public Arrow(double radius, Color fill, double x, double y, double vx, double vy) {
		setType(this.getClass().getSimpleName().toLowerCase());
	    setCanvasSize(10, 20);
	    positionX = x;
	    positionY = y;
	    gc.setFill(Color.RED);
	    gc.fillRect(0, 0, 10, 20);
	    updatePosition();
	    setVelocity(vx, vy);
	    setID(ID.Arrow);
	}
	
    public void handleCollisions(Sprite spriteB) {
//    	System.out.println(spriteB.getType());
    	if (spriteB.isType("entity")) {
    		setActive(false);
    	}
    }
	
    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
        if (node.getTranslateX() > (node.getScene().getWidth() -
                node.getBoundsInParent().getWidth()) ||
                node.getTranslateX() < 0) {
            	setActive(false);
            }
        if (node.getTranslateY() > node.getScene().getHeight() -
            node.getBoundsInParent().getHeight() ||
            node.getTranslateY() < 0) {
        	setActive(false);
        }
//        collidesWall();
    }
    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {
//    	System.out.println("HERE!!!");
//        if (keyCode == KeyCode.SPACE) {
//        	System.out.println("You pressed: " + keyCode);
//        }
    }
}
 