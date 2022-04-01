package com.example.virtualcloset.logic;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.R;
import com.example.virtualcloset.storage.Database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OutfitDataManagerTest {
    Database db = new Database();
    ArrayList<ClothesItem> clothesItems;
    OutfitDataManager newODM;

    @Before
    public void setUp() throws Exception {
        clothesItems = db.getClothesItems();
        newODM = new OutfitDataManager(db);
    }

    @Test
    public void testGetOutfitName() {
        assertEquals(2, newODM.getOutfitName().length);
        assertEquals("WORK", newODM.getOutfitName()[0]);
        assertEquals("CASUAL", newODM.getOutfitName()[1]);
    }

    @Test
    public void testGetID() {
        assertEquals(2,newODM.getID().length);
        assertEquals(0,newODM.getID()[0]);
        assertEquals(1,newODM.getID()[1]);
    }

    @Test
    public void testGetClothesList() {
        assertEquals(2, newODM.getClothesList(0).length); // WORK
        assertEquals(2, newODM.getClothesList(1).length); // CASUAL
    }

    @Test
    public void testGetNames() {
        assertEquals("Gymshark Joggers", newODM.getNames(clothesItems)[0]);
    }

    @Test
    public void testGetTags() {
        assertEquals("|  Black  |  Pants  |  Workout  |  ", newCM.getTags(clothesItems)[0]);
    }

    @Test
    public void testGetImgs() {
        assertEquals(R.drawable.placeholder_1,newCM.getImgs(clothesItems)[1]);
    }

    @After
    public void tearDown() {
    }
}