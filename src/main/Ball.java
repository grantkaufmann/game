package main;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import main.gameengine.Sprite;

public class Ball extends Sprite {
    public Ball(double radius, Color fill) {
    	super();
        Circle sphere = new Circle();
        sphere.setCenterX(radius);
        sphere.setCenterY(radius);
        sphere.setRadius(radius);
        sphere.setFill(fill);
        node = sphere;
        setVelocity(1, 1);
    }
    
    

}
