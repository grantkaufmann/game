package JGame;

import javafx.scene.Group;

public class Level {
    public Group group = new Group();
    public String name = this.getClass().getSimpleName().toLowerCase();
    public void createSprite(String type, double x, double y, String uuid) {}
}
