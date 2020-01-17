package Server;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eeshan
 */

import JGame.nodes.Room;
import JGame.nodes.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static final int P = 80;
    public static ArrayList<Room> roomList;
    public static boolean saveLogs = true;
    public static boolean running = true;
    public static Server server;

    public static void main(String[] args) throws IOException {
        server = new Server();
    }

    public Server() throws IOException {
        ServerSocket ss = new ServerSocket(P);
        System.out.println("Initialized server " + P);

        roomList = new ArrayList<>();
        GameServer gameServer = new GameServer();

        Room room = new Room("Test", gameServer);
        addRoom(room);

        new Thread(gameServer).start();

        while (running) {

            Socket socket = ss.accept();
            System.out.println("Connection established" + socket.getInetAddress().getHostAddress());

            System.out.println("Running?");

            new Thread(new User(socket, room, gameServer)).start();
        }
    }
    
    public static void addRoom(Room s) {
        if (Server.obtainRoom(s.getNumber()) == null) {
            roomList.add(s);
            System.out.println("Room.java created " + s.getNumber());
        }
    }
    
    public static void removeRoom(Room s) {
        if (Server.obtainRoom(s.getNumber()) != null && !s.getNumber().equalsIgnoreCase("Test")) {
            s.shiftRoom(roomList.get(0));
            roomList.remove(s);
            System.out.println("Room.java removed " + s.getNumber());
        }
    }
    
    public static Room obtainRoom(String number) {
        for (Room s : roomList) {
            if (s.getNumber().equalsIgnoreCase(number)) {
                return s;
            }
        }
        return null;
    }
    
    public static Room[] obtainRoom() {
        Room[] s = new Room[roomList.size()];
        for (int i = 0; i < roomList.size(); i++) {
            s[i] = roomList.get(i);
        }
        return s;
    }
    
    public static boolean roomExists(Room s) {
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getNumber().equalsIgnoreCase(s.getNumber())) {
                return true;
            }
        }
        return false;
    }
}
