package JGame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eeshan
 */

import JGame.nodes.Log;
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

    public static void main(String[] args) throws IOException {
        if (saveLogs) 
            System.out.println("Logs enabled"); 
        else 
            System.out.println("Logs disabled");
       
        ServerSocket ss = new ServerSocket(P);
        Log.log("Initialized server " + P);
       
        roomList = new ArrayList<>();
        Room room = new Room("Test");
        addRoom(room);
       
        while (true) {
            Socket socket = ss.accept();
            Log.log("Connection established" + socket.getInetAddress().getHostAddress());
       
            new Thread(new User(socket, room)).start();
            
        }
    }
    
    public static void addRoom(Room s) {
        if (Server.obtainRoom(s.getNumber()) == null) {
            roomList.add(s);
            Log.log("Room.java created " + s.getNumber());
        }
    }
    
    public static void removeRoom(Room s) {
        if (Server.obtainRoom(s.getNumber()) != null && !s.getNumber().equalsIgnoreCase("Test")) {
            s.shiftRoom(roomList.get(0));
            roomList.remove(s);
            Log.log("Room.java removed " + s.getNumber());
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
