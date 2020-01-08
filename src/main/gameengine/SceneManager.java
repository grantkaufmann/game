package main.gameengine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SceneManager {

    private final static List<Group> GAME_GROUPS= new ArrayList<Group>();

    private Game game;
    private Stage stage;
    private int currentStage = 0;

    public SceneManager(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
    }

    public void addScenes(Group... groups) {
        GAME_GROUPS.addAll(Arrays.asList(groups));
    }

    public void removeScenes(Group... groups) {
        GAME_GROUPS.removeAll(Arrays.asList(groups));
    }

    public void setScene(Group group) {
        System.out.println();
        System.out.println("Setting scene");
        Scene scene = new Scene(group, 800, 600);
        game.setSceneNodes(group);
        game.setGameSurface(scene);
        stage.setScene(scene);
        game.spriteManager.initializeSprites();
        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            System.out.println("Setting new group");
            setScene(GAME_GROUPS.get(currentStage = currentStage + 1));
        });
    }
}
