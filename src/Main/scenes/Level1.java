package main.scenes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Ball;

public class Level1 extends Level {
    public Level1(SpriteManager spriteManager, SceneManager sceneManager) {
        Ball b = new Ball(50, Color.PURPLE, 0, 0);
        level.getChildren().add(b.node);
        spriteManager.addSprites(b);
        sceneManager.addScenes(this);
    }
}
