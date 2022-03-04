package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.storage.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataManagerTest {
    Database db = new Database();
    ArrayList<ClothesItem> clothesItems;
    ArrayList<Tag> tags1;
    DataManager newDM;


    //String tags[];
    @Before
    public void setUp() throws Exception {
        clothesItems = new ArrayList<ClothesItem>();
        newDM = new DataManager(db);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void constructorTest() {
        assertEquals("the first cloth's name should match", "Gymshark Joggers", newDM.getNames()[0]);
        assertEquals("the first tag should match", "|  Black  |  Pants  |  Workout  |  ", newDM.getTags()[0]);
        assertEquals("the int img should match", R.drawable.placeholder_1,newDM.getImgs()[1]);
    }

    @Test
    public void getNames() {
        assertEquals("the first cloth's name should match","Gymshark Joggers", newDM.getNames()[0]);
    }

    @Test
    public void getTags() {

        assertEquals("the first tag should match", "|  Black  |  Pants  |  Workout  |  ", newDM.getTags()[0]);
    }

    @Test
    public void getImgs() {
        assertEquals("the int img should match", R.drawable.placeholder_1,newDM.getImgs()[1]);
    }
}