package main;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.gameengine.Game;
import main.gameengine.Sprite;

public class TestGame extends Game {
	
	public TestGame() {
		super(60, "Another");
	}

	@Override
	public void initialize(Stage stage) {
		stage.setTitle(getWindowTitle());
		setSceneNodes(new Group());
		setGameSurface(new Scene(getSceneNodes(), 800, 600));
		stage.setScene(getGameSurface());
		
		Ball b = new Ball(50, Color.PURPLE);
		getSpriteManager().addSprites(b);
		getSceneNodes().getChildren().add(b.node);
		
		
		Sprite puppy = new Sprite();
		puppy.setImage("main/resources/puppy.png");
		Canvas canvas = new Canvas(puppy.image.getWidth(), puppy.image.getHeight());
		puppy.setNode(canvas);
		puppy.setVelocity(2, 2);
		puppy.render(canvas.getGraphicsContext2D());
		
		getSceneNodes().getChildren().add(canvas);
		
		
		getSpriteManager().addSprites(puppy);
		
		
		stage.show();
	}
	
    @Override
    protected void handleUpdate(Sprite sprite) {
        if (sprite instanceof Ball) {
        	Ball sphere = (Ball) sprite;

            // advance the spheres velocity
            sphere.update(1);
           
            // bounce off the walls when outside of boundaries
            if (sphere.node.getTranslateX() > (getGameSurface().getWidth()  -
                sphere.node.getBoundsInParent().getWidth()) ||
                sphere.node.getTranslateX() < 0 ) {
            		sphere.velocityX = sphere.velocityX * -1;
        		}
            if (sphere.node.getTranslateY() > getGameSurface().getHeight()-
                sphere.node.getBoundsInParent().getHeight() ||
                sphere.node.getTranslateY() < 0) {
                	sphere.velocityY = sphere.velocityY * -1;
            }
        } else {
//        	System.out.println(sprite);
//        	getSpriteManager().removeSprites(sprite);
        	
//        	sprite.setVelocity(2, 2);
        	sprite.update(1);
            if (sprite.node.getTranslateX() > (getGameSurface().getWidth()  -
            		sprite.node.getBoundsInParent().getWidth()) ||
            		sprite.node.getTranslateX() < 0 ) {
            	sprite.velocityX = sprite.velocityX * -1;
            		}
                if (sprite.node.getTranslateY() > getGameSurface().getHeight()-
                		sprite.node.getBoundsInParent().getHeight() ||
                		sprite.node.getTranslateY() < 0) {
                	sprite.velocityY = sprite.velocityY * -1;
                }
//        	sprite.render(canvas.getGraphicsContext2D());
  
        	
        }
        
        
    }
}
