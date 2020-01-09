package main;

import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.scenes.Flappy;

public class TestGame extends Game {
	
	public TestGame() {
		super(60, "Flappy Birb");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		sceneManager = new SceneManager(stage, this);
		spriteManager = new SpriteManager(sceneManager);

		spriteManager.listenKeyEvents(true);
		spriteManager.listenMouseEvents(true);

		// Level level1 = new Level1(spriteManager, sceneManager);
		// Level level2 = new Level2(spriteManager, sceneManager);
		// Level level3 = new Level3(spriteManager, sceneManager);

		Level flappy = new Flappy(spriteManager, sceneManager);

		sceneManager.setScene("flappy");

		stage.show();
	}
}
