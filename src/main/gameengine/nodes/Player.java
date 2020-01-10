package main.gameengine.nodes;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import main.prefabs.Arrow;

public class Player extends Sprite {
	public boolean shooting = false;
	private double speed = 1;
	
	public Player() {
		setType("player");
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
    public void handleUpdate() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
//        else if (!main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.W))	{ velocityY = 0; }
        
        
        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.W)) 			{ velocityY = -speed;}
        else if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.S))		{ velocityY = speed;}
        else 																		{ velocityY = 0;}
        
        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.A)) 			{ velocityX = -speed;}
        else if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.D))		{ velocityX = speed;}
        else 																		{ velocityX = 0;}
        
//        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.W)) 			{ velocityY = -speed;
//        } else if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.A)) 	{ velocityX = -speed;
//		} else if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.S)) 	{ velocityY = speed;
//		} else if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.D)) 	{ velocityX = speed;
//		} else {
//			velocityY = 0;
//			velocityX = 0;
//		}

//        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.A)) {
//        	velocityX = -speed;
//    	} else {
//    		velocityX = 0;
//    	}
//        
//        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.S)) {
//        	velocityY = speed;
//    	} else {
//    		velocityY = 0;
//    	}
//        
//        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.D)) {
//        	velocityX = speed;
//    	} else {
//    		velocityX = 0;
//    	}
//       
//
//        if(keyCode == KeyCode.A && isPressed) { velocityX = -speed; }
//        if(keyCode == KeyCode.A && !isPressed) { velocityX = 0; }
//
//        if(keyCode == KeyCode.S && isPressed) { velocityY = speed; }
//        if(keyCode == KeyCode.S && !isPressed) { velocityY = 0; }
//
//        if(keyCode == KeyCode.D && isPressed) { velocityX = speed; }
//        if(keyCode == KeyCode.D && !isPressed) { velocityX = 0; }
//        
//        if(keyCode == KeyCode.SPACE ) { shooting = isPressed; } 
//        if (main.gameengine.Game.keyPressManager.isKeyPressed(KeyCode.SPACE)) {
//      		Arrow arrow = new Arrow(10, Color.RED, node.getTranslateX() + (width / 2), node.getTranslateY(), 0, -15);
//      		main.GrantGame.getSceneNodes().getChildren().add(arrow.getNode());
//      		main.GrantGame.spriteManager.addSprites(arrow);
//        }
        collidesWall();
    }
	
    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {
        // System.out.println("You pressed: " + keyCode);
//        if(keyCode == KeyCode.W && isPressed) { velocityY = -speed; }
//        if(keyCode == KeyCode.W && !isPressed) { velocityY = 0; }
//
//        if(keyCode == KeyCode.A && isPressed) { velocityX = -speed; }
//        if(keyCode == KeyCode.A && !isPressed) { velocityX = 0; }
//
//        if(keyCode == KeyCode.S && isPressed) { velocityY = speed; }
//        if(keyCode == KeyCode.S && !isPressed) { velocityY = 0; }
//
//        if(keyCode == KeyCode.D && isPressed) { velocityX = speed; }
//        if(keyCode == KeyCode.D && !isPressed) { velocityX = 0; }
//        
//        if(keyCode == KeyCode.SPACE ) { shooting = isPressed; }   
      
    }
}
    
