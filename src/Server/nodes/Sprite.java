package Server.nodes;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sprite
{
    public String uuid = UUID.randomUUID().toString();
    public StackPane sp;
    public List animations = new ArrayList<>();
    public double positionX = 0;
    public double positionY = 0;
    public double velocityX = 0;
    public double velocityY = 0;
    public double rotation = 0;
    public double width;
    public double height;
    private boolean active = true;

    public boolean initialActive = active;
    public double initialRotation = rotation;
    public double initialPositionX = positionX;
    public double initialPositionY = positionY;
    public double initialVelocityX = velocityX;
    public double initialVelocityY = velocityY;
    
    ArrayList<String> type = new ArrayList<String>();

    public Sprite()
    {
        setType("sprite");
    }

    public void setInitial() {
        initialActive = active;
        initialRotation = rotation;
        initialPositionX = positionX;
        initialPositionY = positionY;
        initialVelocityX = velocityX;
        initialVelocityY = velocityY;
    }

    public void reset() {
        active = initialActive;
        setRotation(initialRotation);
        setPosition(initialPositionX, initialPositionY);
        setVelocity(initialVelocityX, initialVelocityY);
    }

    public void setUuid(String uuid) {
        if (uuid != null) {
            this.uuid = uuid;
        }
    }

    public boolean getActive() {
    	return active;
    }
    
    public void setActive(boolean active) {
    	this.active = active;
    }
    
    public ArrayList<String> getType() {
    	return type;
    }
    
    public void setType(String type) {
    	this.type.add(type);
    }
    
    public boolean isType(String type) {
    	return this.type.contains(type);
    }

    public void setCanvasSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

    public void setRotation(double deg) {
        rotation = deg;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(double x, double y)
    {
        return getBoundary().intersects(x, y, 1, 1);
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }


    // HELPER METHODS

    public void updatePosition() {
        positionX += velocityX;
        positionY += velocityY;
    }

    public void collidesWall() {
        try {
            if (positionX > (800 -
                width) ||
                positionX < 0) {
                velocityX = velocityX * -1;
                setPosition(positionX, positionY);
            }
            if (positionY > 600 -
                height ||
                positionY < 0) {
                velocityY = velocityY * -1;
                setPosition(positionX, positionY);
            }
        } catch (Exception e) {
            // System.out.printw
        }

    }

    public void handleUpdate() {
        positionX += velocityX;
        positionY += velocityY;
    }

    public Sprite newInstance() { return null; }

    public void handleRender() {}

    public void handleCollisions(Sprite spriteB) {}

    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {}

    public void handleMouseEvent(MouseEvent mouseEvent, boolean isPressed) {}
}
