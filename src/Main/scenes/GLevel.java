package Main.scenes;

import java.util.Random;

import JGame.Level;
import JGame.SceneManager;
import JGame.SpriteManager;
import javafx.scene.paint.Color;
import Main.prefabs.Coin;
import Main.prefabs.Puppy;
import Main.prefabs.Rock;

public class GLevel extends Level {
    public GLevel(SpriteManager spriteManager, SceneManager sceneManager) {
//        Ball b = new Ball(50, Color.PURPLE, 0, 0);
//        level.getChildren().add(b.node);
    	Random rand = new Random(); 
    	
    	for (int i = 0; i < 100; i++) {
			int x = rand.nextInt(800); 
			int y = rand.nextInt(10000); 
    		Rock rock = new Rock(50, Color.PURPLE, x, (y * -1) - 600);
    		rock.setVelocity(0, 3);
    		level.getChildren().add(rock.node);
    		spriteManager.addSprites(rock);
    	}

		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(800); 
			int y = rand.nextInt(600); 
			Coin coin = new Coin(50, Color.BLUE, x, y);
			level.getChildren().add(coin.node);
			spriteManager.addSprites(coin);
		}

		Puppy puppy = new Puppy(100, 400);
		level.getChildren().add(puppy.node);
		spriteManager.addSprites(puppy);
        
		 
        
//        spriteManager.addSprites(b);
        sceneManager.addScenes(this);
    }
}
