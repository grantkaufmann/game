package JGame;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eeshan
 */

import JGame.nodes.User;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkManager {
    
    private Socket _socket;
    private BufferedReader _enter;
    private BufferedWriter _leave;
    private static NetworkManager _instance;
    private ClientManager chatInterface;
    
    public static NetworkManager getInstance() {
        if (_instance == null) {
            _instance = new NetworkManager();
        }
        return _instance;
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
                        System.out.println("Room " + p[1] + "@" + _socket.getInetAddress().getHostAddress());
                    } catch (Exception e) {
                    }
                } else if (packet.startsWith("LIST")) {
                    chatInterface.clearUsers();
                    // chatInterface.cleanList();
                    int count = packet.split("[ ]").length;
                    for (int i = 1; i < count; i++) {
                        User u;
                        u = new User(packet.split("[ ]")[i]);
                        chatInterface.connectUser(u);
                    }
                    
                } else if(!packet.isEmpty()) {
                    chatInterface.handlePacket(packet);
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("DISCONNECTED");
            System.exit(0);
        }
    }
    
    public void setServer(String IP, int port) {
        try {
            _socket = new Socket(IP, port);
            _enter = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
            _leave = new BufferedWriter(new PrintWriter(_socket.getOutputStream()));
        } catch (IOException ex) {
            Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void submit(String var) {
        if (!var.isEmpty()) {
            try {
                _leave.write(var + "\n");
                _leave.flush();
            } catch (IOException ex) {
                Logger.getLogger(NetworkManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String recieve() {
        String s = "";
        try {
            s = _enter.readLine();
        } catch (IOException ex) {
            
        }
        return s;
    }
    
    public void setInterface(ClientManager c) {
        this.chatInterface = c;
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
