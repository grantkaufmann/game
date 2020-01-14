package JGame;

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
        for (Level group : GAME_GROUPS) {
            if (group.name.equals(groupName)) {
                Scene scene = new Scene(group.level, 800, 600);
                JGame.setSceneNodes(group.level);
                JGame.setGameSurface(scene);
                stage.setScene(scene);
                activeScene = scene;
                activeLevel = group;
                JGame.spriteManager.initializeSprites();
//                if (game.spriteManager.listenKeyEvents) { game.spriteManager.listenKeyEvents(scene); }
//                if (game.spriteManager.listenMouseEvents) { game.spriteManager.listenMouseEvents(scene); }
                scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
                    if (key.getCode().toString().equals("P")) {
                        if (getNextLevel() != null) {
                            setScene(getNextLevel().name);
                        } else {
                            System.out.println("Next level does not exist");
                        }
                    }
                });
            }
        }
    }
}
