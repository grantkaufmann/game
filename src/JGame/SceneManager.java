package JGame;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneManager {

    private final static List<Level> GAME_GROUPS= new ArrayList<Level>();

    private JGame JGame;
    private Stage stage;
    public Scene activeScene;
    public Level activeLevel;

    public SceneManager(Stage stage, JGame JGame) {
        this.stage = stage;
        this.JGame = JGame;
    }

    public void addScenes(Level... groups) {
        GAME_GROUPS.addAll(Arrays.asList(groups));
    }

    public void removeScenes(Level... groups) {
        GAME_GROUPS.removeAll(Arrays.asList(groups));
    }
    public Level getNextLevel() {
        Level level = null;
        for (int i = 0; i < GAME_GROUPS.size() - 1; i++) {
            if (GAME_GROUPS.get(i).name.equals(activeLevel.name)) {
                level = GAME_GROUPS.get(i + 1);
            }
        }
        return level;
    }

    public void setScene(String groupName) {
        Level level = null;

        for (Level group : GAME_GROUPS) {
            if (group.name.equals(groupName)) {
                level = group;
            }
        }
        
        if (level == null) {
            Level newLevel = new Level();
            newLevel.level = new Group();
            newLevel.name = groupName;
            addScenes(newLevel);

            level = newLevel;
        }

        Scene scene = new Scene(level.level, 800, 600);
        JGame.setSceneNodes(level.level);
        JGame.setGameSurface(scene);
        stage.setScene(scene);
        activeScene = scene;
        activeLevel = level;
        // JGame.spriteManager.initializeSprites();
        
        
        
    }
}
