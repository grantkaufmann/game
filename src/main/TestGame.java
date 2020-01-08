package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.SceneManager;

import java.util.Random;

public class TestGame extends Game {
	
	public TestGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		stageManager = new SceneManager(stage, this);




		// stage.setScene(getGameSurface());


//		Group level1 = new Group();
//		Ball b = new Ball(50, Color.PURPLE, 0, 0);
//		level1.getChildren().add(b.node);
//		spriteManager.addSprites(b);
//		stageManager.addScenes(level1);









		// spriteManager.listenKeyEvents(getGameSurface());
		// spriteManager.listenMouseEvents(getGameSurface());


		 Group level2 = new Group();
		 Puppy puppy = new Puppy(100, 400);
		 level2.getChildren().add(puppy.node);
		 spriteManager.addSprites(puppy);
		 stageManager.addScenes(level2);


		Group level3 = new Group();
		for (int i = 0; i < 3; i++) {
			Random rand = new Random();
			int x = 200 * i;
			int y = 0;
			Ball j = new Ball(50, Color.PURPLE, x ,y);
			level3.getChildren().add(j.node);
			spriteManager.addSprites(j);

		}
		stageManager.addScenes(level3);



		stageManager.setScene(level3);

		

		stage.show();
	}
}
