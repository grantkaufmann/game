package main;

import javafx.application.Application;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage;
import javafx.scene.shape.LineTo; 
import javafx.scene.shape.MoveTo; 
import javafx.scene.shape.Path; 
import javafx.scene.canvas.Canvas;

public class Main extends Application {
//	Game game = new Game(60, "The Game");

//	TestGame tgame = new TestGame();
	GrantGame ggame = new GrantGame();
	
	@Override 
	public void start(Stage stage) {
	
//		tgame.setDebugMode(true);
//		tgame.initialize(stage);
//		tgame.start();
		
		ggame.setDebugMode(true);
		ggame.initialize(stage);
		ggame.start();
		
	   } 
   public static void main(String args[]){ 
	      launch(args); 
	   } 
}
