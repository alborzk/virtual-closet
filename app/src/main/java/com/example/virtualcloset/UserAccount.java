package com.example.virtualcloset;

import java.util.ArrayList;

/*Notes:
- Not sure if email is necessary
*/

public class UserAccount {

    //Instance Variables
    int id;
    String username;
    String password;
    String email;
    ArrayList<Closet> closets;

    //Constructor
    public UserAccount(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.closets = new ArrayList<Closet>();
    }

    public UserAccount(int id, String username, String password, String email, ArrayList<Closet> closets){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.closets = closets;
    }

    //Getters
    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Closet>

    public int getNumClosets(){
        return closets.size();
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Other Methods
    public boolean addCloset(Closet newCloset){
        return closets.add(newCloset);
    }
}
