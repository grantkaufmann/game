package JGame;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import JGame.nodes.Sprite;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;

public class SpriteManager {

    private SceneManager sceneManager;

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


    public void addSprites(Sprite... sprites) {
        for (Sprite sprite : sprites) {
            JGame.sceneManager.activeLevel.level.getChildren().add( JGame.sceneManager.activeLevel.level.getChildren().size() -1, sprite.node);
            GAME_ACTORS.add(sprite);
        }
    }

    public void addSpriteByType(String type, double x, double y) {
        for (Sprite sprite : GAME_ACTORS) {
            if (sprite.isType(type)) {

                System.out.println("Adding" + type);
                int index = JGame.sceneManager.activeLevel.level.getChildren().size() -1;

                Sprite newSprite = sprite.newInstance();

                System.out.println("Node " + newSprite.node + " - old sprite is " + sprite.node);
                System.out.println("Now " + JGame.sceneManager.activeLevel.level.getChildren().size());

                JGame.sceneManager.activeLevel.level.getChildren().add(newSprite.node);
            }
        }
    }

    public void registerSprites(Sprite... sprites) {
        GAME_ACTORS.addAll(Arrays.asList(sprites));
    }

    public void handlePacket(String type, String x, String y, String uuid) {
        System.out.println("Got a packet");
        // System.out.println("x: " + Float.parseFloat(x) + " y: " + Float.parseFloat(y) );

        Sprite foundGameActor = null;

        for (Sprite gameActor : getSpriteByType(type)) {
            System.out.println("Found an actor");
            foundGameActor = gameActor;
        }

        if (foundGameActor == null) {
            System.out.println("Adding SPRITE " + type);
            // addSpriteByType(type, Double.parseDouble(x), Double.parseDouble(y));
            addSpriteByType("ball", 100, 100);
        } else {
            System.out.println("Updating " + type + " x: " + x + " y: " + y);
            if (Double.parseDouble(x) != 0.0) {
                foundGameActor.positionX = Double.parseDouble(x);
            }
            if (Double.parseDouble(y) != 0.0) {
                foundGameActor.positionY = Double.parseDouble(y);
            }
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
                	JGame.getSceneNodes().getChildren().remove(spriteA.getNode());
                }
            }
        }
    }

    public void handleUpdate() {



//        for (Sprite gameActor : getSpriteByType("paddle")) {
//            System.out.println("Found an actor");
//            // gameActor.setPosition(100, gameActor.positionY += 1);
//            gameActor.positionY += 1;
//        }

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
        List<Sprite> ActiveSprites = SpritesWithoutNulls.stream().filter(p -> p.node.getScene() == JGame.sceneManager.activeScene).collect(Collectors.toList());
        return ActiveSprites;
    }

    /**
     * Removes sprite objects and JGame.nodes from all
     * temporary collections such as:
     * CLEAN_UP_SPRITES.
     * The sprite to be removed will also be removed from the
     * list of all sprite objects called (GAME_ACTORS).
     */
    public void cleanupSprites() {

        // remove from actors list
//    	System.out.println(JGame.Game.getSceneNodes().getChildren());
    	
//    	JGame.Game.getSceneNodes().getChildren().removeAll(CLEAN_UP_SPRITES.stream().map((i) -> i.node).collect(Collectors.toList()));
         GAME_ACTORS.removeAll(CLEAN_UP_SPRITES);

         CLEAN_UP_SPRITES.clear();
    }
}
