package Main.prefabs.pong;
import javafx.scene.paint.Color;
import JGame.nodes.Sprite;

public class ClientBall extends Sprite {

    public ClientBall(double radius, Color fill) {
        setType("ball");
        setCanvasSize(radius * 2, radius * 2);
        gc.setFill(fill);
        gc.fillRect(0, 0, radius * 2, radius * 2);
    }

    public ClientBall newInstance(double radius, Color fill) {
        ClientBall clientBall = new ClientBall(10, Color.BLUE);
        return clientBall;
    }

    public void handleRender() {

    }
}
