package Main.scenes;

import JGame.Level;
import JGame.JGame;
import JGame.nodes.Background;
import Main.prefabs.Ball;
import Main.prefabs.pong.ClientBall;
import Main.prefabs.pong.Paddle;
import javafx.scene.paint.Color;

public class Pong extends Level {
    public Pong() {

        Background background = new Background(null, Color.BLACK);
        JGame.spriteManager.addSprites(background);

        Paddle paddle = new Paddle(40, 300 - 40);
        JGame.spriteManager.addSprites(paddle);

        // Server controlled
        ClientBall clientBall = new ClientBall(10, Color.WHITE);
        JGame.spriteManager.registerSprites(clientBall);
    }
}
