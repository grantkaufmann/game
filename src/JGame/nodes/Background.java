package JGame.nodes;

import javafx.scene.paint.Paint;

public class Background extends Sprite {

    public Background(String filepath, Paint color) {
        setType("background");
        if (filepath != null) {
            setImage(filepath);
            gc.drawImage( image, 0, 0 );
        }
        if (color != null) {
            setCanvasSize(800, 600);
            gc.setFill(color);
            gc.fillRect(0, 0, 800, 600);
        }
    }

}
