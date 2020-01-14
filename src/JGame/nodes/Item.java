package nodes;

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
