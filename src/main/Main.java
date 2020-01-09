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
	TestGame tgame = new TestGame();
	
	@Override 
	public void start(Stage stage) {
//		stage.setTitle("Hello, World!");
//		stage.show();
//		stage.setTitle( "Timeline Example" );
//		 
//	    Group root = new Group();
//	    Scene theScene = new Scene( root );
//	    stage.setScene( theScene );
//	 
//	    Canvas canvas = new Canvas( 512, 512 );
//	    root.getChildren().add( canvas );

//		

		tgame.setDebugMode(true);
		tgame.initialize(stage);
		tgame.start();
		
	   } 
   public static void main(String args[]){ 
	      launch(args); 
	   } 
}
