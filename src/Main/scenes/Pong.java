package Main.scenes;

import JGame.Level;
import JGame.JGame;
import JGame.nodes.Background;
import Main.prefabs.Ball;
import Main.prefabs.pong.ClientBall;
import Main.prefabs.pong.Paddle;
import Main.prefabs.pong.Player;
import javafx.scene.paint.Color;

public class Pong extends Level {
    public Pong() {

        Background background = new Background(null, Color.BLACK);
        JGame.spriteManager.addSprites(background);

        System.out.println("Player number" + JGame.clientManager.getPlayerNumber());

        if (JGame.clientManager.getPlayerNumber() == 1) {
            Player player = new Player(40, 300 - 40);
            JGame.spriteManager.addSprites(player);
        }

        if (JGame.clientManager.getPlayerNumber() == 2) {
            Player player = new Player(800 - 40, 300 - 40);
            JGame.spriteManager.addSprites(player);
        }


        // Server controlled
        ClientBall clientBall = new ClientBall(10, Color.WHITE);
        JGame.spriteManager.registerSprites(clientBall);

//        Paddle paddle = new Paddle(800 - 40, 300 - 40);
//        JGame.spriteManager.registerSprites(paddle);
    }
}
