package JGame.nodes;

public class CreateRequest {
    public String type;
    public double x;
    public double y;
    public String uuid;

    public CreateRequest(String type, double x, double y, String uuid) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.uuid = uuid;
    }
}
