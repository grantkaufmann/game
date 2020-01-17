package Main;
import JGame.*;
import javafx.stage.Stage;
import Main.scenes.Pong;

public class TestGame extends JGame {
	
	public TestGame() {
		super(60, "Flappy Birb");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		// stage.initStyle(StageStyle.UNDECORATED);

		keyPressManager = new KeyboardManager();
		sceneManager = new SceneManager(stage, this);
		sceneManager.setScene("pong");
		spriteManager = new SpriteManager();

		networkManager = new NetworkManager();

		currentLevel = new Pong();



		// JGame.networkManager.submit("ball", 100, 100, "454353535345345345345");

		stage.show();
	}
}
