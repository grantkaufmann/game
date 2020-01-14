package Main.scenes;

import JGame.Level;
import JGame.SceneManager;
import JGame.SpriteManager;
import Main.prefabs.Puppy;

public class Level2 extends Level {
    public Level2(SpriteManager spriteManager, SceneManager sceneManager) {
        Puppy puppy = new Puppy(100, 400);
        level.getChildren().add(puppy.node);
        spriteManager.addSprites(puppy);
        sceneManager.addScenes(this);
    }
}
