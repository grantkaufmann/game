package main.gameengine.nodes;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import main.ID;
import main.TestGame;

public class Sprite
{
    public Image image;
    public Canvas canvas;
    public GraphicsContext gc;
    public Node node;
    public ID id;
    public List animations = new ArrayList<>();
    public double positionX = 0;
    public double positionY = 0;
    public double velocityX = 0;
    public double velocityY = 0;
    public double width;
    public double height;
    private boolean active = true;
    ArrayList<String> type = new ArrayList<String>();

    public Sprite()
    {
        Canvas canvas = new Canvas();
        this.canvas = canvas;
        this.node = canvas;
        this.gc = canvas.getGraphicsContext2D();
        setType("Sprite");
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

    public void setID(ID id) {
        this.id = id;
    }

    public void setCanvasSize(double width, double height) {
        this.width = width;
        this.height = height;
        canvas.setWidth(width);
        canvas.setHeight(height);
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        setCanvasSize(width, height);
    }

    public void setImage(String filename)
    {
       Image i = new Image(filename);
       setImage(i);
    }

    public void setPosition(double x, double y)
    {
        node.setTranslateX(x += velocityX);
        node.setTranslateY(y += velocityY);
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(node.getTranslateX(),node.getTranslateY(),width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.node.getBoundsInParent().intersects(node.getBoundsInParent());
    }

    public boolean intersects(double x, double y)
    {
        return node.getBoundsInParent().intersects(x, y, 1, 1);
    }
    
    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
    
    public void setNode(Node n) {
    	node = n;
    }
    
    public Node getNode() {
    	return node;
    }

    // HELPER METHODS

    public void updatePosition() {
        node.setTranslateX(positionX += velocityX);
        node.setTranslateY(positionY += velocityY);
    }

    public void collidesWall() {
        try {
            if (node.getTranslateX() > (node.getScene().getWidth() -
                node.getBoundsInParent().getWidth()) ||
                node.getTranslateX() < 0) {
            	System.out.println(positionX);
                velocityX = velocityX * -1;
                setPosition(positionX, positionY);
            }
            if (node.getTranslateY() > node.getScene().getHeight() -
                node.getBoundsInParent().getHeight() ||
                node.getTranslateY() < 0) {
                velocityY = velocityY * -1;
                setPosition(positionX, positionY);
            }
        } catch (Exception e) {
            // System.out.printw
        }

    }

    public void initialize() {}

    public void handleUpdate() {}

    public void handleRender() {}

    public void handleCollisions(Sprite spriteB) {}

    public void handleKeyEvent(KeyCode keyCode, boolean isPressed) {}

    public void handleMouseEvent(MouseEvent mouseEvent, boolean isPressed) {}
}
