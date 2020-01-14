package Main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
//	JGame.Game game = new JGame.Game(60, "The JGame.Game");

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
