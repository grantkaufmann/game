package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.KeyboardManager;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Puppy;
import main.scenes.GLevel;
import main.scenes.Level1;

import java.util.Random;

public class GrantGame extends Game {
	
	public GrantGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		sceneManager = new SceneManager(stage, this);
		spriteManager = new SpriteManager(sceneManager);
		keyPressManager = new KeyboardManager();
//		spriteManager.listenKeyEvents(true);
//		spriteManager.listenMouseEvents(true);
		Level glevel = new GLevel(spriteManager, sceneManager);
		sceneManager.setScene("glevel");
		stage.show();
	}
}

