package nodes;

public class Entity extends Sprite {
//	public int health = 100;
	
	public Entity() {
		setType("entity");
	}
	
//    public void handleCollisions(Sprite spriteB) {
//    	if (spriteB.isType("player")) {
//    		setPosition(node.getTranslateX() + spriteB.velocityX, node.getTranslateY() + spriteB.velocityY);
//    	}
//    	if (spriteB.isType("arrow")) {
//    		Arrow sprite = (Arrow)spriteB;
//    		health = health - sprite.damage;
//    	}
//    	if (health <= 0) {
//    		setActive(false);
//    	}
//    	collidesWall();
//    }
    
}
