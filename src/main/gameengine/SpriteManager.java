package main.gameengine;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.gameengine.nodes.Sprite;

import java.util.*;
import java.util.stream.Collectors;

public class SpriteManager {

    private SceneManager sceneManager;
//    public boolean listenKeyEvents = false;
//    public boolean listenMouseEvents = false;

    public SpriteManager(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
    /** All the sprite objects currently in play */
    private final static List<Sprite> GAME_ACTORS = new ArrayList<Sprite>();

    /** A global single threaded list used to check collision against other
     * sprite objects.
     */
    private final static List<Sprite> CHECK_COLLISION_LIST = new ArrayList<Sprite>();

    /** A global single threaded set used to cleanup or remove sprite objects
     * in play.
     */
    private final static Set CLEAN_UP_SPRITES = new HashSet<>();

    /** */
    public List<Sprite> getAllSprites() {
        return GAME_ACTORS;
    }

    public void listenKeyEvents(boolean listen) {
//        listenKeyEvents = listen;
    }

    public void listenKeyEvents(Scene scene) {
//        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
//            for (Sprite gameActor : GAME_ACTORS) {
//                gameActor.handleKeyEvent(key.getCode(), true);
//            }
//        });
//
//        scene.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> { 
//            for (Sprite gameActor : GAME_ACTORS) {
//        		gameActor.handleKeyEvent(key.getCode(), false);
//            }
//        });
    }

    public void listenMouseEvents(boolean listen) {
//        listenMouseEvents = listen;
    }

    public void listenMouseEvents(Scene scene) {
        scene.addEventHandler(MouseEvent.MOUSE_PRESSED, (mouseEvent) -> {
            for (Sprite gameActor : GAME_ACTORS) {
                gameActor.handleMouseEvent(mouseEvent, true);
            }
        });

        scene.addEventHandler(MouseEvent.MOUSE_RELEASED, (mouseEvent) -> {
            for (Sprite gameActor : GAME_ACTORS) {
                gameActor.handleMouseEvent(mouseEvent, false);
            }
        });
    }

    /**
     * VarArgs of sprite objects to be added to the game.
     * @param sprites
     */
    public void addSprites(Sprite... sprites) {
        GAME_ACTORS.addAll(Arrays.asList(sprites));
       
    }

    public void initializeSprites() {
        List<Sprite> activeSprites = getActiveSprites();

        for (Sprite gameActor : activeSprites) {
            gameActor.initialize();
        }
    }

    /**
     * VarArgs of sprite objects to be removed from the game.
     * @param sprites
     */
    public void removeSprites(Sprite... sprites) {
        GAME_ACTORS.removeAll(Arrays.asList(sprites));
    }

    /** Returns a set of sprite objects to be removed from the GAME_ACTORS.
     * @return CLEAN_UP_SPRITES
     */
    public Set getSpritesToBeRemoved() {
        return CLEAN_UP_SPRITES;
    }

 /**
     * Adds sprite objects to be removed
     * @param sprites varargs of sprite objects.
     */
    public void addSpritesToBeRemoved(Sprite... sprites) {
        if (sprites.length > 1) {
            CLEAN_UP_SPRITES.addAll(Arrays.asList((Sprite[]) sprites));
        } else {
            CLEAN_UP_SPRITES.add(sprites[0]);
        }
    }

    /**
     * Returns a list of sprite objects to assist in collision checks.
     * This is a temporary and is a copy of all current sprite objects
     * (copy of GAME_ACTORS).
     * @return CHECK_COLLISION_LIST
     */
    public List getCollisionsToCheck() {
        return CHECK_COLLISION_LIST;
    }

    /**
     * Clears the list of sprite objects in the collision check collection
     * (CHECK_COLLISION_LIST).
     */
    public void resetCollisionsToCheck() {
        CHECK_COLLISION_LIST.clear();
        CHECK_COLLISION_LIST.addAll(GAME_ACTORS);
    }

    public List<Sprite> getSpriteByType(String type) {
        List<Sprite> activeSprites = getActiveSprites();

        List<Sprite> sprites = new ArrayList<>(Collections.emptyList());

        for (Sprite sprite : activeSprites) {
            if (sprite.isType(type)) {
                sprites.add(sprite);
            }
        }

        return sprites;
    }

    public void checkCollisions() {
        List<Sprite> activeSprites = getActiveSprites();
        for (Sprite spriteA : activeSprites) {
            for (Sprite spriteB : activeSprites) {
                if (spriteA != spriteB && spriteA.intersects(spriteB)) {
                    spriteA.handleCollisions(spriteB);
                }
                if (!spriteA.getActive()) {
                	addSpritesToBeRemoved(spriteA);
                	main.gameengine.Game.getSceneNodes().getChildren().remove(spriteA.getNode());
                }
            }
        }
    }

    public void handleUpdate() {
        try {
            List<Sprite> activeSprites = getActiveSprites();
            for (Sprite gameActor : activeSprites) {
                gameActor.handleUpdate();
            }
            for (Sprite gameActor : activeSprites) {
                gameActor.handleRender();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Sprite> getActiveSprites() {
        List<Sprite> SpritesWithoutNulls = GAME_ACTORS.stream().filter(p -> p.node.getScene() != null).collect(Collectors.toList());
        List<Sprite> ActiveSprites = SpritesWithoutNulls.stream().filter(p -> p.node.getScene() == sceneManager.activeScene).collect(Collectors.toList());
        return ActiveSprites;
    }

    /**
     * Removes sprite objects and nodes from all
     * temporary collections such as:
     * CLEAN_UP_SPRITES.
     * The sprite to be removed will also be removed from the
     * list of all sprite objects called (GAME_ACTORS).
     */
    public void cleanupSprites() {

        // remove from actors list
//    	System.out.println(main.gameengine.Game.getSceneNodes().getChildren());
    	
//    	main.gameengine.Game.getSceneNodes().getChildren().removeAll(CLEAN_UP_SPRITES.stream().map((i) -> i.node).collect(Collectors.toList()));
         GAME_ACTORS.removeAll(CLEAN_UP_SPRITES);

         CLEAN_UP_SPRITES.clear();
    }
}
