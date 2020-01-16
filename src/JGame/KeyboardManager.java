package JGame;

import java.util.HashMap;

import JGame.nodes.Sprite;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class KeyboardManager {
	HashMap<KeyCode,Object> keyState = new HashMap<>();
	private Scene scene;
	public void setScene(Scene scene) {
		this.scene = scene;
		this.scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
			KeyCode k = key.getCode();

			if (key.getCode().getName().equals("Esc")) {
				JGame.exit();
			}

			if (keyState.containsKey(k)) {
				keyState.replace(k, true);
			} else {
				keyState.put(k, true);
			}
            for (Sprite gameActor : JGame.spriteManager.getAllSprites()) {
                gameActor.handleKeyEvent(k, true);
            }
        });
		this.scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
			KeyCode k = key.getCode();
			if (keyState.containsKey(k)) {
				keyState.replace(k, false);
			} else {
				keyState.put(k, false);
			}
            for (Sprite gameActor : JGame.spriteManager.getAllSprites()) {
        		gameActor.handleKeyEvent(k, false);
            }
        });
	}
	
	public boolean isKeyPressed(KeyCode key) {
		if (!keyState.containsKey(key)) {
			return false;
		}
		return (boolean) keyState.get(key);
	}
}
