package JGame;

import JGame.nodes.User;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NetworkManager {

    private Socket socket;
    private BufferedReader enter;
    private BufferedWriter leave;

    public String nick = UUID.randomUUID().toString();

    public List<User> users = new ArrayList<User>();

    public NetworkManager(String ip, int port) {
        try {
            setServer(ip, port);
            submit("NICK " + nick);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        hearServer();
                    } catch (Exception e) {
                    }
                }
            }).start();
        } catch (Exception e) {
            System.out.println("Server not responding");
        }
    }

    public void setServer(String IP, int port) {
        try {
            socket = new Socket(IP, port);
            enter = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            leave = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void hearServer() {
        System.out.println("Hearing server");
        initializeHeartBeat();
        try {
            while (true) {
                String packet = recieve();
                if (packet.startsWith("200")) {

                } else if (packet.startsWith("400")) {
                    System.out.println("DISCONNECTED");
                    System.exit(0);
                } else if (packet.startsWith("500")) {
                    System.out.println("ERROR");
                } else if (packet.startsWith("Room")) {
                    String[] p = packet.split("[ ]");
                    try {
                        System.out.println("Room " + p[1] + "@" + socket.getInetAddress().getHostAddress());
                    } catch (Exception e) {
                    }
                } else if (packet.startsWith("LIST")) {
                    clearUsers();
                    // chatInterface.cleanList();
                    int count = packet.split("[ ]").length;
                    for (int i = 1; i < count; i++) {
                        User u;
                        u = new User(packet.split("[ ]")[i]);
                        connectUser(u);
                    }

                } else if(!packet.isEmpty()) {
                    handlePacket(packet);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("DISCONNECTED");
            System.exit(0);
        }
    }

    public void submit(String var) {
        if (!var.isEmpty()) {
            try {
                leave.write(var + "\n");
                leave.flush();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public void submit(String type, double newX, double newY, String uuid) {
        submit(type + " " + newX + " " + newY + " " + uuid + " \n");
    }

    public String recieve() {
        String s = "";
        try {
            s = enter.readLine();
        } catch (IOException ex) {

        }
        return s;
    }

    public void clearUsers() {
        System.out.println("Clearing Users");
        users.clear();
    }

    public void connectUser(User u) {
        // System.out.println(u + " Got user " + u.getNick());
        System.out.println("I am " + nick);
        System.out.println("user" + u.getNick());
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

    private void initializeHeartBeat() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long heartbeat = System.currentTimeMillis();
                while (true) {
                    if (System.currentTimeMillis() - heartbeat >= 5000) {
                        submit("BEAT " + System.currentTimeMillis());
                        heartbeat = System.currentTimeMillis();
                    }
                }
            }
        }).start();
    }
}
