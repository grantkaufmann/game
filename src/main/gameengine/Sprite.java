package main.gameengine;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import main.TestGame;

public class Sprite
{
    public Image image;
    public Canvas canvas;
    public GraphicsContext gc;
    public Node node;
    public List animations = new ArrayList<>();
    public double positionX;
    public double positionY;
    public double velocityX;
    public double velocityY;
    public double width;
    public double height;

    public Sprite()
    {
        Canvas canvas = new Canvas();
    	System.out.println("Sprite");
        setNode(canvas);
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        positionX = 0;
        positionY = 0;    
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        canvas.setWidth(width);
        canvas.setWidth(height);
    }

    public void setImage(String filename)
    {
       Image i = new Image(filename);
       setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
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
    
    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
        if (node != null) {
            node.setTranslateX(positionX);
            node.setTranslateY(positionY);
        }
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
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

    public void initialize() {}

    public void handleUpdate() {}

    public void handleRender() {}
}
