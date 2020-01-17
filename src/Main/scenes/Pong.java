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

        // System.out.println("Client Manager: " + JGame.clientManager.getPlayerNumber());

        createSprite("player", 40, 300 - 40, null);

//        if (JGame.clientManager.getPlayerNumber() == 1) {
//            System.out.println("Adding player");
//
//        }
//
//        if (JGame.clientManager.getPlayerNumber() == 2) {
//            createSprite("player", 800 - 40, 300 - 40, null);
//        }

        // createSprite("ball", 100, 100, null);
    }

    public void createSprite(String type, double x, double y, String uuid) {
        System.out.println("got here");
        switch (type) {
            case "player": {
                double newX = 40;
                if (JGame.clientManager.getPlayerNumber() == 2) {
                    newX = 800 - 40;
                }
                Player player = new Player(newX, y, JGame.clientManager.nick);
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
                double newX = 800 - 40;
                if (JGame.clientManager.getPlayerNumber() == 2) {
                    newX = 40;
                }
                Paddle paddle = new Paddle(newX, y, uuid);
                JGame.spriteManager.addSprites(paddle);
                break;
            }
        }
    }
}
