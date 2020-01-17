package Server;

import JGame.JGame;
import JGame.nodes.CreateRequest;
import JGame.nodes.Room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GameServer implements Runnable {

    public Socket s;
    public Room room;
    private BufferedWriter _leave;
    private NetworkManager net;

    private HashMap<String, CreateRequest> lastKnownPos = new HashMap<String, CreateRequest>();

    public GameServer() {
        net = NetworkManager.getInstance();
        try {
            net.setServer("localhost", 80);
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

    public void submit(String type, double newX, double newY, String uuid) {
        net.submit(type + " " + newX + " " + newY + " " + uuid + " \n");
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
        System.out.println("Got here, now here is S: " + s);
        if (s.contains(":")) {
            System.out.println("ADDING" + s);
            String[] splitter = s.split(" ");
            String type = splitter[1];
            String posX = splitter[2];
            String posY = splitter[3];
            String uuid = splitter[4];
            lastKnownPos.put(uuid, new CreateRequest(type, Double.parseDouble(posX), Double.parseDouble(posY), uuid));
            System.out.println("Last known is now: " + lastKnownPos.size());
        }
    }

    public void sendLastKnownPositions() {
        for (Map.Entry i : lastKnownPos.entrySet()) {
            // System.out.println("Key: "+me.getKey() + " & Value: " + me.getValue());
            CreateRequest create = (CreateRequest) i.getValue();
            submit(create.type, create.x, create.y, create.uuid);
        }
    }
}
