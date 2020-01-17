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

        // System.out.println("Client Manager: " + JGame.networkManager.getPlayerNumber());

//        if (JGame.networkManager.getPlayerNumber() == 1) {
//            System.out.println("Adding player");
//
//        }
//
//        if (JGame.networkManager.getPlayerNumber() == 2) {
//            createSprite("player", 800 - 40, 300 - 40, null);
//        }

        // createSprite("ball", 100, 100, null);
    }

    public void createSprite(String type, double x, double y, String uuid) {
        System.out.println("got here");
        switch (type) {
            case "player": {
                Player player = new Player(x, y, uuid);
                JGame.spriteManager.addSprites(player);
                break;
            }
            case "ball": {
                System.out.println("Scene is adding Ball");
                 ClientBall clientBall = new ClientBall(x, y, uuid);
                 JGame.spriteManager.addSprites(clientBall);
                break;
            }
            case "paddle": {
                Paddle paddle = new Paddle(x, y, uuid);
                JGame.spriteManager.addSprites(paddle);
                break;
            }
        }
    }
}
