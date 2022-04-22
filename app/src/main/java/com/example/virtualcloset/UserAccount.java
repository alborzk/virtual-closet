package com.example.virtualcloset;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAccount implements Serializable {

    //Instance Variables
<<<<<<< HEAD
    int id;
    String username;
    String password;
    String email;
    ArrayList<Closet> closets;
    int img;
=======
    private int id;
    private String username;
    private String password;
    private String email;
    private ArrayList<Closet> closets;
>>>>>>> origin/sysTest2

    //Constructor
    public UserAccount(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.closets = new ArrayList<Closet>();
        this.img = R.drawable.user_icon;
    }

    public UserAccount(int id, String username, String password, String email, ArrayList<Closet> closets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.closets = closets;
        this.img = R.drawable.user_icon;
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

    public ArrayList<Closet> getClosets() {
        return closets;
    }

    public int getNumClosets() {
        return closets.size();
    }

    public int getImg() {
        return img;
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

    public void setImg(int img) {
        this.img = img;
    }

    //Add a new closet to the account
    public boolean addCloset(Closet newCloset){
        return closets.add(newCloset);
    }

}
