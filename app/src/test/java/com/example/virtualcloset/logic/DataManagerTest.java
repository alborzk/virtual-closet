package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.storage.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataManagerTest {
    Database db = new Database(false);
    DataManager newDM;


    //String tags[];
    @Before
    public void setUp() throws Exception {
        newDM=new DataManager(db);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void constructorTest() {
        assertNotNull(db);
        assertNotNull(newDM);

    }

    @Test
    public void findAccount() {
        assertNotNull(newDM.findAccount("user","password"));

    }

    @Test
    public void findAID() {
        assertEquals("Should return a account with ID:0",0,newDM.findAccount("user","password").getID());
    }

    @Test
    public void accountExists() {
        assertTrue("Should return True if found an exist account", newDM.accountExists("user"));
        assertFalse("Should return False if found an exist account", newDM.accountExists("user2"));
    }
}
