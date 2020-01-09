package main.scenes;

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import main.Coin;
import main.Rock;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Ball;
import main.prefabs.Puppy;

public class GLevel extends Level {
    public GLevel(SpriteManager spriteManager, SceneManager sceneManager) {
//        Ball b = new Ball(50, Color.PURPLE, 0, 0);
//        level.getChildren().add(b.node);
        
		Rock rock = new Rock(50, Color.PURPLE, 200, 200);
		level.getChildren().add(rock.node);
		spriteManager.addSprites(rock);
		
		Random rand = new Random(); 
		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(800); 
			int y = rand.nextInt(600); 
			Coin coin = new Coin(50, Color.BLUE, x, y);
			level.getChildren().add(coin.node);
			spriteManager.addSprites(coin);
		}

		
		Ball c = new Ball(50, Color.RED, 600, 200);
		level.getChildren().add(c.node);
		spriteManager.addSprites(c);
		
		 Puppy puppy = new Puppy(100, 400);
		 level.getChildren().add(puppy.node);
		 spriteManager.addSprites(puppy);
        
        
//        spriteManager.addSprites(b);
        sceneManager.addScenes(this);
    }
}
