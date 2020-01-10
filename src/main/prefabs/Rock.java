package main.prefabs;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.ID;
import main.gameengine.nodes.Entity;
import main.gameengine.nodes.Sprite;

public class Rock extends Entity {
	public int health = 100;
	Canvas healthBarCanvas = new Canvas();
	public GraphicsContext healthBarGC;
	Rectangle healtbarTank1;
    public Rock(double radius, Color fill, int x, int y) {
    	
    	healtbarTank1 = new Rectangle(radius, 5, Color.GREEN);
    	setType(this.getClass().getSimpleName().toLowerCase());
        setID(ID.Rock);
        setCanvasSize(radius * 2, radius * 2 + 10);
        positionX = x;
        positionY = y;
        gc.setFill(fill);
        gc.fillRect(0, 10, radius * 2, radius * 2); 
        healtbarTank1.setTranslateY(-50);
        node = new StackPane(canvas, healtbarTank1);
        updatePosition();
    }
    
    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
    }
    
    public void handleCollisions(Sprite spriteB) {
    	if (spriteB.isType("player")) {
    		setPosition(node.getTranslateX() + spriteB.velocityX, node.getTranslateY() + spriteB.velocityY);
    	}
    	if (spriteB.isType("arrow")) {
    		Arrow sprite = (Arrow)spriteB;
    		health = health - sprite.damage;
    		healtbarTank1.setScaleX(health * .01);
    	}
    	if (health <= 0) {
    		setActive(false);
    	}
//    	collidesWall();
    }
}
