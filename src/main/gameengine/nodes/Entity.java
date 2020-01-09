package main.gameengine.nodes;

public class Entity extends Sprite {
	public Entity() {
		setType("Entity");
	}
	
    public void handleCollisions(Sprite spriteB) {
    	if (spriteB.isType("Player")) {
    		System.out.println(node.getTranslateX());
    		System.out.println(spriteB.velocityX);
    		setPosition(node.getTranslateX() + spriteB.velocityX, node.getTranslateY() + spriteB.velocityY);
//    		setPosition(60, 60);
    	}
    	collidesWall();

    }
}
