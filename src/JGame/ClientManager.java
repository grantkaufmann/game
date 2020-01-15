package JGame;

import JGame.nodes.Sprite;
import JGame.nodes.User;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

    public NetworkManager net;

    public List<User> users = new ArrayList<User>();

    public ClientManager() {
        net = NetworkManager.getInstance();
        try {
            net.setServer("localhost", 80);
            net.setInterface(this);
            net.submit("NICK " + "Justin");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        net.hearServer();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Server not responding");
        }
    }

    public void submit(String str) {
        net.submit(str);
    }

    public void clearUsers() {
        System.out.println("Clearing Users");
        users.clear();
    }

    public void connectUser(User u) {
        // System.out.println(u + " Got user " + u.getNick());
        users.add(u);
    }

    public void handlePacket(String packet) {
        System.out.println("Got packet" + packet);
    }

    public List<User> getConnectedUsers() {
        return users;
    }
}
