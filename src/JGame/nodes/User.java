package JGame.nodes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eeshan
 */


import Server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User implements Runnable {
    
    private String _nick;
    private BufferedReader _entry;
    private BufferedWriter _leave;
    private long _loginTime;
    private boolean _connected, _superUser, _tickerOn;
    private String _IP;
    private long _ping;
    private Room _room;
    private long _lastBeat;

    public User(String nick) {
        this._nick = nick;
    }
    
    public User(Socket s, Room room) throws IOException {
        this._room = room;
        this._loginTime = System.currentTimeMillis();
        this._IP = s.getInetAddress().getHostAddress();
        this._ping = 0;
        this._superUser = false;
        this._tickerOn = true;
        _entry = new BufferedReader(new InputStreamReader(s.getInputStream()));
        _leave = new BufferedWriter(new PrintWriter(s.getOutputStream()));
    }

    @Override
    public void run() {
        String login = recieve();
       
        if (!login.startsWith("NICK")) {
            submit("Invalid");
            System.out.println("Invalid Login: " + login);
            _connected = false;
        } 
        else {
                _connected = !_room.existUser(this);
            }
       
        if (_connected) {
            _nick = login.split("[ ]")[1];
            submit(_room.enter(this));
            submitUserList();
            if (_tickerOn) {
                _lastBeat = System.currentTimeMillis();
                asyncBeatCheck();
            }
            submit("Room.java " + _room.getNumber());
            do {
                String packet = recieve();
                if (packet != null && !packet.isEmpty()) {
                    try {
                        analyze(packet);
                    } catch (Exception e) {
                        
                    }
                }
            } while (_connected);
            submit("Disconnected");
            _room.removeUser(this);
        }
    }
    
    public void analyze(String s) {
        if (s.startsWith("EXIT")) { 
            _connected = false;
        } 
        else if (s.startsWith("BEAT")) {
            String[] p;
            p = s.split("[ ]");
            if (p.length > 2) {
                submit("Invalid syntax");
                System.out.println("Invalid: " + s);
            } else {
                
                _lastBeat = System.currentTimeMillis();
                
                _ping = System.currentTimeMillis() - Long.parseLong(p[1]);
            }
        }
        else if (s.startsWith("/NICK ")) { 
            String[] p;
            p = s.split("[ ]");
            if (p.length > 2) {
                submit("Incorrect syntax");
                System.out.println("Invalid: " + s);
            } else {
                
                String oldname = _nick;
                if (_room.existUser(new User(p[1]))) {
                    submit("There is already a user named" + _nick + "in the room");
                    _nick = oldname;
                } else {
                    if (!(p[1].length() > 12)) {
                        _nick = p[1];
                        _room.updatedUserList();
                        System.out.println(oldname + " has changed name" + _nick);
                        _room.spread(oldname + " has changed name " + _nick);
                
                        submit("OK");
                    } 
                    else {
                        submit("Max 12 characters nickname");
                    }
                }
            }
        } 
        else if (s.startsWith("/user ")) { 
            
            String[] p;
            p = s.split("[ ]");
            if (p.length > 2) {
                submit("Incorrect syntax");
                System.out.println("Invalid: " + s);
            } else {
                
                if (!_room.existUser(new User(p[1]))) {
                    submit("No user with this name " + p[1]);
                } else {
                
                    User tmp = _room.getUser(p[1]);
                
                    submit("======================\nName: " + tmp.getNick() + "\nIP: " + tmp.getIP() + "\nPing: " + tmp.getPing() + "ms\nEntry: " + new Date(tmp.getLoginTime()) + "======================");
                    System.out.println(_nick + " on " + tmp.getNick());
                }
            }
        } 
        else if (s.startsWith("/HELP")) {
            String[] p;
            p = s.split("[ ]");

            submit("----------------\nCommands\n-----------------\n- /user <usr>: Display user info");
            submit("- /P <usr> <msg>: Send private message\n- /NICK <new>: Change username");
            submit("- /C <name> [PW]: Create a new room.\n- /J <name> [PW]: Change to room");
            submit("- /LIST: List available rooms\n-\n- EXIT: Exit\n======================");
        }  else if (s.startsWith("/P")) { 
            String[] p;
            p = s.split("[ ]");
            if (!_room.existUser(new User(p[1]))) {
                submit("No user with this name" + p[1]);
            } else {
            
                User tmp = _room.getUser(p[1]);
            
                _room.submitPrivateMessage(this, tmp, s.substring(3+tmp.getNick().length()+1));
                System.out.println("Private message  " + this.getNick() + " y " + tmp.getNick() + ": " + s.substring(3+tmp.getNick().length()));
            }
        }   

        else if (s.startsWith("/C ")) { 
            String[] p;
            p = s.split("[ ]");
            
            if (p.length > 3) {
                submit("Invalid syntax");
                System.out.println("Invalid: " + s);
            } else {
                
                if (!Server.roomExists(new Room(p[1]))) {
                    Room sl = null;
                
                    if (p.length == 2) {
                
                        sl = new Room(p[1]);
                    } else if (p.length == 3) { 
                        sl = new Room(p[1], p[2]);
                    }
                    if (sl != null) {
                        Server.addRoom(sl);
                        _room.removeUser(this);
                        sl.enter(this);
                        _room = sl;
                        submit("Room.java" + _room.getNumber());
                        _room.updatedUserList();
                    }
                } 
                else {
                    submit("Room.java already exists with that name");
                }
            }
        } 
        else if (s.startsWith("/J ")) { 
            String[] p;
            p = s.split("[ ]");
            
            if (p.length > 3) {
                submit("Invalid syntax");
                System.out.println("Invalid: " + s);
            } else {
                if (Server.roomExists(new Room(p[1]))) {
                        if (p.length == 2) {
                            Room sl = Server.obtainRoom(p[1]);
                
                            if (sl.hasPassword()) {
                                submit("Requires password");
                            }
                            else { 
                                _room.removeUser(this);
                                sl.enter(this);
                                _room = sl;
                                submit("Room.java" + _room.getNumber());
                                _room.updatedUserList();
                            }
                        } 
                        else if (p.length == 3) {
                            Room sl = Server.obtainRoom(p[1]);
                            
                            if (!sl.getPassword().equalsIgnoreCase(p[2])) {
                                submit("Password incorrect");
                            } else { 
                                _room.removeUser(this);
                                sl.enter(this);
                                _room = sl;
                                submit("Room.java" + _room.getNumber());
                                _room.updatedUserList();
                            }
                        }
                } 
                else { 
                    submit("No room" + p[1]);
                }
            }
        } 
        else if (s.startsWith("/LIST")) { 
            Room[] sl = Server.obtainRoom();
            
            submit("===========================");
            submit("Rooms available: " + sl.length);
            submit("===========================");
            for (Room sl1 : sl) {
                submit(sl1.getNumber() + " - Users: " + sl1.getUserCount() + ((sl1.hasPassword())?" (password)":""));
            }
            submit("===========================");
        }
 
        else { 
            if (s.length() < 140) {
                
                _room.spread(_nick + ": " + s);
                if (_nick.equals("Host")) {
                    System.out.println(s);
                } else {
                    System.out.println("Received message" + _nick + " in room" + _room.getNumber() + ". content: " + s);
                }

            } else {
                System.out.println("Longer message" + _nick);
            }
        }
    }
    
    public void submitUserList() {
        StringBuilder strb = new StringBuilder();
        strb.append("LIST ");
        for (User usr : _room.getUsers()) {
            strb.append(usr.getNick());
            strb.append(" ");
        }
        submit(strb.toString());
    }
    
    public void submit(String s) {
        try {
            _leave.write(s + "\n");
            _leave.flush();
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String recieve() {
        String s = "";
        try {
            s = _entry.readLine();
        } catch (Exception ex) {}
        return s;
    }

    public String getNick() {
        return _nick;
    }

    public void setNick(String nick) {
        this._nick = nick;
    }

    public long getLoginTime() {
        return _loginTime;
    }

    public void setLoginTime(long loginTime) {
        this._loginTime = loginTime;
    }

    public String getIP() {
        return _IP;
    }

    public void setIP(String IP) {
        this._IP = IP;
    }

    public long getPing() {
        return _ping;
    }

    public void setPing(long ping) {
        this._ping = ping;
    }

    public boolean isSuperUser() {
        return _superUser;
    }

    public void setSuperUser(boolean superUser) {
        this._superUser = superUser;
    }

    public boolean isConnected() {
        return _connected;
    }

    public void setConnection(boolean coonect) {
        this._connected = coonect;
    }

    private void asyncBeatCheck() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                
                while (_connected) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {}
                
                    if (System.currentTimeMillis() - _lastBeat >= 7000) {
                
                        submit("Offline");
                        _connected = false;
                        System.out.println(_nick + " Inactive");
                    }
                }
            }
        }).start();
    }

    public Room getRoom() {
        return _room;
    }

    public void setRoom(Room room) {
        this._room = room;
    }
    
}
