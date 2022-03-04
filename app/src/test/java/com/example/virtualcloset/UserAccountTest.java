package com.example.virtualcloset;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UserAccountTest {
    /*
        Should we keep it simple only one closet for one user?
        closets arraylist should be initialized at beginning;
        missing get closet method.

     */
    ArrayList<Closet> ClosetArray1;
    Closet c1;
    UserAccount newUser;
    UserAccount newUser2;

    @Before
    public void setUp() throws Exception {
        ClosetArray1= new ArrayList<Closet>();
        ClosetArray1.add(c1);
        newUser = new UserAccount(9, "igumi", "0728", "umanitoba.ca", ClosetArray1);
        newUser2 = new UserAccount(8, "hideonbush", "1234", "google.com");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructorTest() {
        assertEquals("id should match", 9, newUser.getID());
        assertEquals("Username should match", "igumi", newUser.getUsername());
        assertEquals("Password should match", "0728", newUser.getPassword());
        assertEquals("Email should match", "umanitoba.ca", newUser.getEmail());
        assertEquals("Each user's closet should match", 1, newUser.getNumClosets());
    }

    @Test
    public void constructorTest2() {
        assertEquals("id should match", 8, newUser2.getID());
        assertEquals("Username should match", "hideonbush", newUser2.getUsername());
        assertEquals("Password should match", "1234", newUser2.getPassword());
        assertEquals("Email should match", "google.com", newUser2.getEmail());
        assertEquals("Each user's closet should match", 0, newUser2.getNumClosets());
    }

    @Test
    public void getID() {
        assertEquals("id should match", 9, newUser.getID());
    }

    @Test
    public void getUsername() {
        assertEquals("Username should match", "igumi", newUser.getUsername());
    }

    @Test
    public void getPassword() {
        assertEquals("Password should match", "0728", newUser.getPassword());
    }

    @Test
    public void getEmail() {
        assertEquals("Email should match", "umanitoba.ca", newUser.getEmail());
    }

    @Test
    public void getCloset(){
        assertEquals("return the ideal closet object", ClosetArray1,newUser.getClosets());
    }
    @Test
    public void getNumClosets() {
        assertEquals("Each user's closet should match", 1, newUser.getNumClosets());
    }

    @Test
    public void setId() {
        int randomId = (int) (Math.random() * 10); //generate random ID
        System.out.println(randomId);
        newUser.setId(randomId);
        assertEquals("id should match", randomId, newUser.getID());
    }


    @Test
    public void setUsername() {
        newUser.setUsername("NewName");
        assertEquals("Username should match after setUsername", "NewName", newUser.getUsername());
    }

    @Test
    public void setEmail() {
        newUser.setEmail("google.com");
        assertEquals("Email should match after setEmail", "google.com", newUser.getEmail());
    }

    @Test
    public void setPassword() {
        newUser.setPassword("1234");
        assertEquals("Password should match after setPassword", "1234", newUser.getPassword());
    }

    @Test
    public void addCloset() {
        assertEquals("Each user's closet should match", true, newUser.addCloset(c1));
    }
}