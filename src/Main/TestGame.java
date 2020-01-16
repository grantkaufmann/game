package Main;
import JGame.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import Main.scenes.Pong;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

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

		clientManager = new ClientManager();

		currentLevel = new Pong();



		// JGame.clientManager.submit("ball", 100, 100, "454353535345345345345");

		stage.show();
	}
}
