package Main.scenes;

import JGame.Level;
import JGame.JGame;
import JGame.nodes.Background;
import Main.prefabs.pong.Paddle;
import javafx.scene.paint.Color;

public class Pong extends Level {
    public Pong() {
        JGame.sceneManager.addScenes(this);

        Background background = new Background(null, Color.BLACK);

        JGame.spriteManager.addSprites(background);

        Paddle paddle = new Paddle(40, 300 - 40);
        // level.getChildren().add(paddle.node);
        // JGame.spriteManager.addSprites(paddle);



    }
}
