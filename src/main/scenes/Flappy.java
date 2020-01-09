package main.scenes;

import javafx.scene.paint.Color;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.FlappyBG;
import main.prefabs.FlappyBird;
import main.prefabs.FlappyPipe;
import main.prefabs.Puppy;

import java.util.Random;

public class Flappy extends Level {
    public Flappy(SpriteManager spriteManager, SceneManager sceneManager) {

        for (int i = 0; i <= 10; i++) {
            FlappyBG flappyBG2 = new FlappyBG(1024 * i, 0);
            level.getChildren().add(flappyBG2.node);
            spriteManager.addSprites(flappyBG2);
        }


        for (int i = 1; i <= 100; i++) {

            Random rand = new Random();
            int r = rand.nextInt(4);

            int botY = 500 - (r * 100);
            int topY = botY - 750;

            FlappyPipe pipe = new FlappyPipe(500 * i, botY, 0);
            level.getChildren().add(pipe.node);
            spriteManager.addSprites(pipe);

            FlappyPipe pipe2 = new FlappyPipe(500 * i, topY, 180);
            level.getChildren().add(pipe2.node);
            spriteManager.addSprites(pipe2);
        }

        FlappyBird bird = new FlappyBird(20, 100, spriteManager, sceneManager);
        level.getChildren().add(bird.node);
        spriteManager.addSprites(bird);


        sceneManager.addScenes(this);
    }
}
