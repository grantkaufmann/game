package main.gameengine.nodes;

import javafx.scene.input.KeyCode;

public class Player extends Sprite {
	private double speed = 1;
	
	public Player() {
		setType("Player");
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return speed;
	}
	
    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {
        // System.out.println("You pressed: " + keyCode);
        if(keyCode == KeyCode.W && isPressed) { velocityY = -speed; }
        if(keyCode == KeyCode.W && !isPressed) { velocityY = 0; }

        if(keyCode == KeyCode.A && isPressed) { velocityX = -speed; }
        if(keyCode == KeyCode.A && !isPressed) { velocityX = 0; }

        if(keyCode == KeyCode.S && isPressed) { velocityY = speed; }
        if(keyCode == KeyCode.S && !isPressed) { velocityY = 0; }

        if(keyCode == KeyCode.D && isPressed) { velocityX = speed; }
        if(keyCode == KeyCode.D && !isPressed) { velocityX = 0; }
    }
}
