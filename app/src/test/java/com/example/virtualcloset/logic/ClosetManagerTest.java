package com.example.virtualcloset.logic;

import static org.junit.Assert.assertEquals;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.Tag;
import com.example.virtualcloset.storage.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ClosetManagerTest {
    Closet c;
    ArrayList<ClothesItem> clothesItems;
    ClosetManager newCM;

    @Before
    public void setUp() throws Exception {
        clothesItems = new ArrayList<ClothesItem>();
        newCM = new ClosetManager(c);
    }

    @Test
    public void testGetNames() {
        assertEquals("Gymshark Joggers", newCM.getNames()[0]);
    }

    @Test
    public void testGetTags() {
        assertEquals("|  Black  |  Pants  |  Workout  |  ", newCM.getTags()[0]);
    }

    @Test
    public void testGetImgs() {
        assertEquals(R.drawable.placeholder_1,newCM.getImgs()[1]);
    }

    @After
    public void tearDown() {
    }
}