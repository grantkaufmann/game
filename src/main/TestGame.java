package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.Sprite;

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
		
		// Ball b = new Ball(50, Color.PURPLE);
		// spriteManager.addSprites(b);
		// getSceneNodes().getChildren().add(b.node);
		

		Puppy puppy = new Puppy();
		spriteManager.addSprites(puppy);

		// puppy.render(canvas.getGraphicsContext2D());
		

		stage.show();
	}
}
