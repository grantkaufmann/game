package main.gameengine.nodes;

import javafx.stage.Stage;

public class Item extends Sprite {
		
	public Item() {
		setType("item");
	}
	
    public void handleCollisions(Sprite spriteB) {
    	if (spriteB.isType("player")) {
    		setActive(false);
    	}
    }
}
