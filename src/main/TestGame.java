package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;

public class TestGame extends Game {
	
	public TestGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		setSceneNodes(new Group());
		setGameSurface(new Scene(getSceneNodes(), 800, 600));
		stage.setScene(getGameSurface());
		spriteManager.listenKeyEvents(getGameSurface());


		Ball b = new Ball(50, Color.PURPLE);
		spriteManager.addSprites(b);
		getSceneNodes().getChildren().add(b.node);
		

		Puppy puppy = new Puppy(100, 400);
		spriteManager.addSprites(puppy);
		getSceneNodes().getChildren().add(puppy.node);
		

		stage.show();
	}
}
