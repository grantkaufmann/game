package main.gameengine.nodes;

import javafx.stage.Stage;

public class Item extends Sprite {
		
	public Item() {
		setType("item");
	}
	
    public void handleCollisions(Sprite spriteB) {
//    	System.out.println(spriteB.getType());
    	if (spriteB.isType("player")) {
    		setActive(false);
    	}
    }
}
