package main.gameengine;

import com.sun.javafx.perf.PerformanceTracker;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
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
import javafx.util.Duration;
import main.gameengine.Sprite;

public abstract class Game {
	private static Timeline gameLoop;
	private final int framesPerSecond;
	private final String windowTitle;
	private boolean debug = false;
	private boolean running = true;
	Label debugLabel = new Label("a label");
	
	private static PerformanceTracker tracker;
	
	private Scene gameSurface;
	private Group sceneNodes;
    private final SpriteManager spriteManager = new SpriteManager();	
	
    public Game(final int fps, final String title) {
        framesPerSecond = fps;
        windowTitle = title;
        // create and set timeline for the game loop
        run();
    }
    
    public abstract void initialize(final Stage stage);
    
    protected final void buildAndSetGameLoop() {
    	
//        final Duration oneFrameAmt = Duration.millis(1000/getFramesPerSecond());
//        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
//            new EventHandler() {
//                @Override
//                public void handle(Event event) {
                    // update actors
//                    updateSprites();
                    // check for collision
//                    checkCollisions();
                    // removed dead things
//                    cleanupSprites();
//                }
//        }); // oneFrame

        // sets the game world's game loop (Timeline)
//        setGameLoop(TimelineBuilder.create()
//                .cycleCount(Animation.INDEFINITE)
//                .keyFrames(oneFrame)
//                .build());
    }
    private void run() {
    	final Duration oneFrameAmt = Duration.millis(1000/getFramesPerSecond());
    	final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (debug) {
					debugLabel.setText(
							String.format("Averate FPS: %.3f \n", tracker.getAverageFPS()) +
							String.format("Instant FPS rate: %.3f", tracker.getInstantFPS())
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
//      gameLoop.play();


//    	long lastTime = System.nanoTime();
//    	double ns = 1000000000 / getFramesPerSecond();
//    	double delta = 0;
//    	int updates = 0;
//    	int frames = 0;
//    	long timer = System.currentTimeMillis();
//    	while(running) {
//    		long now = System.nanoTime();
//    		delta += (now - lastTime) / ns;
//    		lastTime = now;
//    		if (delta >= 1) {
//    			tick();
//    			updates++;
//    			delta--;
//    		}
//    		render();
//    		frames++;
//    		
//    		if (System.currentTimeMillis() - timer > 1000) {
//    			timer += 1000;
//    			if (debug) {
//        			System.out.println(updates + " ticks, Fps " + frames);
//    			}
//    			updates = 0;
//    			frames = 0;
//    		}
//    	}
//    	stop();
    }
    
    protected void updateSprites() {
        for (Sprite sprite:spriteManager.getAllSprites()){
        	handleUpdate(sprite);
        }
    }
    
    protected void handleUpdate(Sprite sprite) {}
    
    public void checkCollisions() {}
    protected void cleanupSprites() {
        spriteManager.cleanupSprites();
    }
    
	public void tick() {}
	public void render() {}
	public void stop() {
//		running = false;
		getGameLoop().stop();
	}
	public void start() {
//		running = true;
//		run();
//		System.out.println(getGameLoop());
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
	}
	
	public Group getSceneNodes() {
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
    

    /**
     * Returns the sprite manager containing the sprite objects to
     * manipulate in the game.
     * @return SpriteManager The sprite manager.
     */
    protected SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
