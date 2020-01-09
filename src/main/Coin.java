package main;

import javafx.scene.paint.Color;
import main.gameengine.nodes.Item;

public class Coin extends Item {
    public Coin(double radius, Color fill, int x, int y) {
    	setType(this.getClass().getSimpleName().toLowerCase());
        setCanvasSize(10, 20);
        positionX = x;
        positionY = y;
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, 10, 20);
        updatePosition();
        setID(ID.Coin);
    }
}
