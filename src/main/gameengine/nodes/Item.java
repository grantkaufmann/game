package main.gameengine.nodes;

import javafx.stage.Stage;

public class Item extends Sprite {
		
	public Item() {
		setType("Item");
	}
	
    public void handleCollisions(Sprite spriteB) {
    	if (spriteB.isType("Player")) {
    		setActive(false);
    	}
    }
}
