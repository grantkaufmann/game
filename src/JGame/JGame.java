package JGame;

import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public abstract class JGame {
	private static Timeline gameLoop;
	private final int framesPerSecond;
	private final String windowTitle;
	private boolean debug = false;
	Label debugLabel = new Label("");
	
	private static PerformanceTracker tracker;
	
	private Scene gameSurface;
	public static SceneManager sceneManager;
	private static Group sceneNodes;
    public static SpriteManager spriteManager;
    public static KeyboardManager keyPressManager;
	public static ClientManager clientManager;
	
    public JGame(final int fps, final String title) {
        framesPerSecond = fps;
        windowTitle = title;
        run();
    }

	public abstract void initialize(final Stage stage);

    private void run() {
    	final Duration oneFrameAmt = Duration.millis(1000/getFramesPerSecond());
    	final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (debug) {
					debugLabel.setText(
							String.format("Averate FPS: %.3f \n", tracker.getAverageFPS()) +
							String.format("Instant FPS rate: %.3f \n", tracker.getInstantFPS()) +
							String.format("Rendered Objects: %s \n", spriteManager.getActiveSprites().size()) +
							String.format("Players: %s", clientManager.getConnectedUsers().size())
					);
				}
				updateSprites();
				// check for collision
				checkCollisions();
				// removed dead things
				cleanupSprites();
			}
      }); // oneFrame

      // sets the game world's game loop (Timeline)
      Timeline timeline = new Timeline();
      timeline.setCycleCount(Timeline.INDEFINITE);
      timeline.getKeyFrames().add(oneFrame);
      setGameLoop(timeline);
    }
    
    protected void updateSprites() {
    	try {
			spriteManager.handleUpdate();
		} catch(Exception e) {
    		System.out.println("No sprite manager initialized. Unable to update.");
		}
    }

    public void checkCollisions() {
		try {
			spriteManager.checkCollisions();
		} catch(Exception e) {
			System.out.println("No sprite manager initialized. Unable to check collisions.");
		}
	}
    protected void cleanupSprites() {
		try {
			spriteManager.cleanupSprites();
		} catch(Exception e) {
			System.out.println("No sprite manager initialized. Unable to cleanup sprites.");
		}
    }

	public void stop() {
		getGameLoop().stop();
	}
	public void start() {
		getGameLoop().play();
	}
    
    protected int getFramesPerSecond() {
        return framesPerSecond;
    }
    
    protected String getWindowTitle() {
        return windowTitle;
    }
    
    protected boolean getDebugMode() {
        return debug;
    }
    
    public void setDebugMode(boolean debugMode) {
        debug = debugMode;
    }
    
	public Scene getGameSurface() {
		return gameSurface;
	}
	
	protected void setGameSurface(Scene gameSurface) {
		tracker = PerformanceTracker.getSceneTracker(gameSurface);

		this.gameSurface = gameSurface;
		if (keyPressManager != null) {
			keyPressManager.setScene(gameSurface);
		}
	}
	
	public static Group getSceneNodes() {
		return sceneNodes;
	}
	
	protected void setSceneNodes(Group sceneNodes) {
		this.sceneNodes = sceneNodes;
		debugLabel.setWrapText(true);
		this.sceneNodes.getChildren().add(new Pane(debugLabel));
	}
	
	protected static Timeline getGameLoop() {
	  return gameLoop;
	}

	protected static void setGameLoop(Timeline timeline) {
	  gameLoop = timeline;
	}
	
    private float getFPS () {
        float fps = tracker.getAverageFPS();
        tracker.resetAverageFPS();
        return fps;
    }
}
