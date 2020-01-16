package Server;

import JGame.nodes.Room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class GameServer implements Runnable {

    public Socket s;
    public Room room;
    private BufferedWriter _leave;
    private NetworkManager net;

    public GameServer() throws IOException, InterruptedException {
        Thread.sleep(3000);
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
            while(true){

                // tick and print fps
                long lastTick = System.nanoTime();
                timer.tick();
                System.out.println(timer.getFPS());


//                ballX += velocity;
//                ballY += velocity;
//                submit("ball", ballX, ballY, uuid);

                // sleep
                long wake = lastTick + sleep;
                do {Thread.sleep(0);}
                while (System.nanoTime() < wake);
            }
        } catch (InterruptedException e) {
            // oh someone paused the game
        }
    }
}
