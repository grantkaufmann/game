package JGame.nodes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eeshan
 */
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Room {
    private ArrayList<User> _users;
    private String _userName;
    private String _password;
    private ArrayList<User> _bans;
    
    public Room(String number) {        //Creates a Room without a password
        this._userName = number;
        this._password = "";
        this._users = new ArrayList<>();
        this._bans = new ArrayList<>();
    }
    
    public Room(String number, String pw) {     //Creates a Room without a password
        this._userName = number;
        this._password = pw;
        this._users = new ArrayList<>();
        this._bans = new ArrayList<>();
    }
    
    public String enter(User u) {       //Enter user without password set
        if (!existUser(u)) {
       
                u.setConnection(true);
                _users.add(u);
       
                spread(u.getNick() + " Entered the room " + this._userName);
       
                updatedUserList();
                Log.log(u.getNick() + " Entered the room " + this._userName);
       
                u.submit("Room " + this._userName);
                return "OK";
        } 
        else {
            u.setConnection(false);
            return "User already in room";
        }
    }
    
    public String enter(User u, String pw) {    //Enter user with password set
        if (!existUser(u)) {
       
            if (pw.equals(this._password)) {
                    u.setConnection(true);
                    _users.add(u);
                    spread(u.getNick() + " Entered the room " + this._userName);
                    updatedUserList();
                    Log.log(u.getNick() + " Entered the room " + this._userName);
       
                    u.submit("Room " + this._userName);
                    return "OK";
            } 
                else {
       
                return "Incorrect pass";
            }
        } else {
           
            u.setConnection(false);
            return "User already present";
        }
    }
    
    public boolean hasPassword() {
        return !this._password.isEmpty();
    }
    
    public void removeUser(User u) {        //Remove the user
        if (existUser(u)) {
            _users.remove(u);
            spread(u.getNick() + "" + this._userName);
            updatedUserList();
            Log.log(u.getNick() + "" + this._userName);
        }
    }
    
    public boolean existUser(User u) {      //Check if user already exists
        for (User usr : _users) {
            if (usr.getNick().equalsIgnoreCase(u.getNick())) {
                return true;
            }
        }
        return false;
    }
    
    public void spread(String m) {
        for (User usr : _users) {
            usr.submit(m);
        }
    }
    
    public User getUser(String nick) {
        for (User usr : _users) {
            if (usr.getNick().equalsIgnoreCase(nick)) {
                return usr;
            }
        }
        return null;
    }
    
    public void addBan(User u) {
        _bans.add(u);
    }
    
    public void Quit(String usr) {
        for (int i = 0; i < _bans.size(); i++) {
            if (_bans.get(i).getNick().equals(usr)) {
                _bans.remove(i);
                break;
            }
        }
    }
    
    public void submitPrivateMessage(User de, User a, String m) {   //Submit a private message between users in same chat room
        de.submit("(Private)" + de.getNick() + ": " + m);
        a.submit("(Private)" + de.getNick() + ": " + m);
    }
    
    public void shiftRoom(Room destination) {       //Change rooms
        try {
            for (int i = _users.size()-1; i >= 0; i--) {
                _users.get(0).setRoom(destination);
                destination.enter(_users.get(0));
                removeUser(_users.get(0));
            }
       
            destination.updatedUserList();
        } catch (ConcurrentModificationException ex) {}
        finally {updatedUserList();}
    }

    public String getNumber() {
        return _userName;
    }

    public void setNumber(String number) {
        this._userName = number;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }
    
    public int getUserCount() {
        return _users.size();
    }

    public ArrayList<User> getUsers() {
        return _users;
    }

    public void updatedUserList() {
        for (User usr : _users) {
            usr.submitUserList();
        }
    }
}
