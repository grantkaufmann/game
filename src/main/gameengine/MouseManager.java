package main.gameengine;

import java.util.ArrayList;
import java.util.HashMap;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import main.gameengine.nodes.Sprite;


public class MouseManager {
	HashMap<MouseButton, Object> keyState = new HashMap<>();
	private Scene scene;
	public void setScene(Scene scene) {
		this.scene = scene;
		
		this.scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> {
			MouseButton k = mouseEvent.getButton();
			if (keyState.containsKey(k)) {
				keyState.replace(k, mouseEvent);
			} else {
				keyState.put(k, mouseEvent);
			}
			
            for (Sprite gameActor : main.gameengine.Game.spriteManager.getAllSprites()) {
                gameActor.handleMouseEvent(mouseEvent, true);
            }
        });
		this.scene.addEventHandler(MouseEvent.MOUSE_RELEASED, (mouseEvent) -> {
			MouseButton k = mouseEvent.getButton();
			if (keyState.containsKey(k)) {
				keyState.replace(k, false);
			} else {
				keyState.put(k, false);
			}
            for (Sprite gameActor : main.gameengine.Game.spriteManager.getAllSprites()) {
            	gameActor.handleMouseEvent(mouseEvent, false);
            }
        });
	}
	
	public boolean isMousePressed(MouseButton mouseButton) {
		if (!keyState.containsKey(mouseButton)) {
			return false;
		}
		return (boolean) keyState.get(mouseButton);
	}
}
