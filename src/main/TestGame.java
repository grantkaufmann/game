package main;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Ball;
import main.prefabs.Puppy;
import main.scenes.Level1;
import main.scenes.Level2;
import main.scenes.Level3;

import java.util.Random;

public class TestGame extends Game {
	
	public TestGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		sceneManager = new SceneManager(stage, this);
		spriteManager = new SpriteManager(sceneManager);

		spriteManager.listenKeyEvents(true);
		spriteManager.listenMouseEvents(true);

		Level level1 = new Level1(spriteManager, sceneManager);
		Level level2 = new Level2(spriteManager, sceneManager);
		Level level3 = new Level3(spriteManager, sceneManager);

		sceneManager.setScene("level1");

		stage.show();
	}
}
