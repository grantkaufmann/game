package Main;
import JGame.*;
import javafx.stage.Stage;
import Main.scenes.Flappy;

public class TestGame extends JGame {
	
	public TestGame() {
		super(60, "Flappy Birb");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		sceneManager = new SceneManager(stage, this);
		spriteManager = new SpriteManager(sceneManager);
		keyPressManager = new KeyboardManager();

//		spriteManager.listenKeyEvents(true);
//		spriteManager.listenMouseEvents(true);

		// JGame.Level level1 = new Level1(spriteManager, sceneManager);
		// JGame.Level level2 = new Level2(spriteManager, sceneManager);
		// JGame.Level level3 = new Level3(spriteManager, sceneManager);

		Level flappy = new Flappy(spriteManager, sceneManager, keyPressManager);

		sceneManager.setScene("flappy");

		stage.show();
	}
}
