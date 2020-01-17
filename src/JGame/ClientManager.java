package JGame;

import JGame.nodes.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientManager {

    public NetworkManager net;
    public String nick = UUID.randomUUID().toString();

    public List<User> users = new ArrayList<User>();

    public ClientManager() {
        net = NetworkManager.getInstance();
        try {
            net.setServer("localhost", 80);
            net.setInterface(this);
            net.submit("NICK " + nick);

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

    public void submit(String type, double newX, double newY, String uuid) {
        net.submit(type + " " + newX + " " + newY + " " + uuid + " \n");
    }

    public void clearUsers() {
        System.out.println("Clearing Users");
        users.clear();
    }

    public void connectUser(User u) {
        // System.out.println(u + " Got user " + u.getNick());
        users.add(u);
    }

    public int getPlayerNumber() {
        int playerNumber = 0;
        // System.out.println("Connected Users " + getConnectedUsers().size());
        for (int i = 0; i < getConnectedUsers().size(); i ++) {
            if (getConnectedUsers().get(i).getNick().equals(nick)) {
                playerNumber = i + 1;
            }
        }

        if (playerNumber == 0) {
            JGame.exit();
        }

        return playerNumber;
    }

    public void handlePacket(String packet) {

        if (packet.contains(":") && !packet.startsWith(nick)) {
            System.out.println(packet);
        // if (packet.contains(":")) {
            // System.out.println(packet);
            String[] splitter = packet.split(" ");
            String type = splitter[1];
            String posX = splitter[2];
            String posY = splitter[3];
            String uuid = splitter[4];

            JGame.spriteManager.handlePacket(type, posX, posY, uuid);
        }
    }

    public List<User> getConnectedUsers() {
        return users.stream().filter(i -> !i.getNick().equals("Host")).collect(Collectors.toList());
    }
}
