package main.scenes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Ball;

import java.util.Random;

public class Level3 extends Level {
    public Level3(SpriteManager spriteManager, SceneManager sceneManager) {
        for (int i = 0; i < 3; i++) {
            Random rand = new Random();
            int x = 200 * i;
            int y = 0;
            Ball j = new Ball(50, Color.PURPLE, x ,y);
            level.getChildren().add(j.node);
            spriteManager.addSprites(j);
        }
        sceneManager.addScenes(this);
    }
}
