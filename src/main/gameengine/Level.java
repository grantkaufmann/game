package main.gameengine;

import javafx.scene.Group;

public class Level {
    public Group level = new Group();
    public String name = this.getClass().getSimpleName().toLowerCase();
}
