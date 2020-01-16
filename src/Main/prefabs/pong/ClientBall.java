package Main.prefabs.pong;
import javafx.scene.paint.Color;
import JGame.nodes.Sprite;

public class ClientBall extends Sprite {

    private int radius = 10;

    public ClientBall(double x, double y, String uuid) {
        setType("ball");
        setUuid(uuid);
        setPosition(x, y);
        setCanvasSize(radius * 2, radius * 2);
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, radius * 2, radius * 2);
    }

    public void handleRender() {

    }
}
