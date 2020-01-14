package main.scenes;

import javafx.scene.Group;
import main.gameengine.Level;
import main.gameengine.SceneManager;
import main.gameengine.SpriteManager;
import main.prefabs.Puppy;

public class Level2 extends Level {
    public Level2(SpriteManager spriteManager, SceneManager sceneManager) {
        Puppy puppy = new Puppy(100, 400);
        level.getChildren().add(puppy.node);
        spriteManager.addSprites(puppy);
        sceneManager.addScenes(this);
    }
}
