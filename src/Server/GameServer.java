package Server;

import JGame.nodes.CreateRequest;
import JGame.nodes.Room;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

public class GameServer implements Runnable {

    public Socket s;
    public Room room;
    private BufferedWriter _leave;
    private NetworkManager net;

    private Properties prop = new Properties();
    private InputStream config;

    private HashMap<String, CreateRequest> lastKnownPos = new HashMap<String, CreateRequest>();

    public GameServer() {

        try {
            config = new FileInputStream("src/config.txt");
            prop.load(config);
        } catch(IOException e) {
            e.printStackTrace();
        }


        net = NetworkManager.getInstance();
        try {
            net.setServer(prop.getProperty("host"), 80);
            net.setInterface(this);
            net.submit("NICK " + "Host");

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

    public void submit(String type, double newX, double newY, String uuid, boolean hasLastKnownPosition) {
        net.submit(type + " " + newX + " " + newY + " " + uuid + " " + hasLastKnownPosition + " \n");
    }

    public void run() {
        double ballX = 50;
        double ballY = 50;

        double velocity = 0.2;

        String uuid = UUID.randomUUID().toString();

        double desiredFPS = 60d;
        long sleep = (long) (ServerGameTimer.ONE_SECOND / desiredFPS);
        ServerGameTimer timer = new ServerGameTimer();
        timer.start();

        try {
            while (true) {

                // tick and print fps
                long lastTick = System.nanoTime();
                timer.tick();
                // System.out.println(timer.getFPS());


//                ballX += velocity;
//                ballY += velocity;
//                submit("ball", ballX, ballY, uuid);

                // sleep
                long wake = lastTick + sleep;
                do {
                    Thread.sleep(0);
                }
                while (System.nanoTime() < wake);
            }
        } catch (InterruptedException e) {
            // oh someone paused the game
        }
    }

    public void addLastKnownPositions(String s) {
        if (s.contains(":")) {
            String[] splitter = s.split(" ");
            String type = splitter[1];
            String posX = splitter[2];
            String posY = splitter[3];
            String uuid = splitter[4];
            lastKnownPos.put(uuid, new CreateRequest(type, Double.parseDouble(posX), Double.parseDouble(posY), uuid));
            System.out.println("Last known is now: " + lastKnownPos.size());
        }
    }

    public void sendLastKnownPositions(String uuid, int playerNumber) {
        for (Map.Entry i : lastKnownPos.entrySet()) {
            // System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
            CreateRequest create = (CreateRequest) i.getValue();
            submit(create.type, create.x, create.y, create.uuid, true);
        }

        if (!lastKnownPos.containsKey(uuid)) {
            System.out.println(uuid + " is player #" + playerNumber);
            double newX = Double.parseDouble(prop.getProperty("startX"));
            if (playerNumber == 2) {
                newX = 800 - 40;
            }
            submit(prop.getProperty("playerType"), newX, Double.parseDouble(prop.getProperty("startY")), uuid, lastKnownPos.containsKey(uuid));
        }
    }
}
