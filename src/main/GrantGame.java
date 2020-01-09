package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.SceneManager;

import java.util.Random;

public class GrantGame extends Game {
	
	public GrantGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		sceneManager = new SceneManager(stage, this);


//		stage.setScene(getGameSurface());
		

		Group level1 = new Group();
		
		Rock rock = new Rock(50, Color.PURPLE, 200, 200);
		level1.getChildren().add(rock.node);
		spriteManager.addSprites(rock);
		
		Random rand = new Random(); 
		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(800); 
			int y = rand.nextInt(600); 
			Coin coin = new Coin(50, Color.BLUE, x, y);
			level1.getChildren().add(coin.node);
			spriteManager.addSprites(coin);
		}

		
//		Ball c = new Ball(50, Color.RED, 600, 200);
//		level1.getChildren().add(c.node);
//		spriteManager.addSprites(c);
		
		 Puppy puppy = new Puppy(100, 400);
		 level1.getChildren().add(puppy.node);
		 spriteManager.addSprites(puppy);
		 sceneManager.addScenes(level1);
		 sceneManager.setScene(level1);
//		spriteManager.listenKeyEvents(getGameSurface());
		// spriteManager.listenMouseEvents(getGameSurface());


//		 Group level2 = new Group();

//		 stageManager.addScenes(level2);
//
//
//		Group level3 = new Group();
//		for (int i = 0; i < 3; i++) {
//			Random rand = new Random();
//			int x = 300 * i;
//			int y = 200;
//			Ball j = new Ball(50, Color.PURPLE, x ,y);
//			level3.getChildren().add(j.node);
//			spriteManager.addSprites(j);
//
//		}
//		level3.getChildren().add(puppy.node);
//		stageManager.addScenes(level3);
//
//
//
		stage.show();
	}
}

