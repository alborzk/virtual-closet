package com.example.virtualcloset;

import java.io.Serializable;
import java.util.ArrayList;

/*Notes:
- Not sure if email is necessary
*/

public class UserAccount implements Serializable {

    //Instance Variables
    int id;
    String username;
    String password;
    String email;
    ArrayList<Closet> closets;
    int img;

    //Constructor
    public UserAccount(int id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.closets = new ArrayList<Closet>();
        this.img = R.drawable.user_icon;
    }

    public UserAccount(int id, String username, String password, String email, ArrayList<Closet> closets){
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

    public ArrayList<Closet> getClosets(){
        return closets;
    }

    public int getNumClosets(){
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

    //Other Methods

    public boolean addCloset(Closet newCloset){
        return closets.add(newCloset);
    }

}
