package main;

import javafx.scene.paint.Color;
import main.gameengine.nodes.Entity;

public class Rock extends Entity {
    public Rock(double radius, Color fill, int x, int y) {
    	setType("Rock");
        setID(ID.Rock);
        setCanvasSize(radius * 2, radius * 2);
        positionX = x;
        positionY = y;
        gc.setFill(fill);
        gc.fillRect(0, 0, radius * 2, radius * 2);
        updatePosition();

    }
}
