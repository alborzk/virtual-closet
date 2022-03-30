package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.storage.Database;

import org.junit.Test;

import java.util.ArrayList;

public class OutfitDataManagerTest {
    Database db = new Database();
    ArrayList<ClothesItem> clothesItems;
    OutfitDataManager newDM = new OutfitDataManager(db);

    @Test
    public void testGetOutfitName() {
        assertEquals(2, newDM.getOutfitName().length);
        assertEquals("WORK", newDM.getOutfitName()[0]);
        assertEquals("CASUAL", newDM.getOutfitName()[1]);
    }

    @Test
    public void getID() {
        //OufitDataManager newDM = new OufitDataManager(db);
        assertEquals(2,newDM.getID().length);
        assertEquals(0,newDM.getID()[0]);
        assertEquals(1,newDM.getID()[1]);
    }

    @Test
    public void testGetClothesList() {
        assertEquals(2, newDM.getClothesList(0).length); // WORK
        assertEquals(2, newDM.getClothesList(1).length); // CASUAL
    }

    @Test
    public void testGetNames() {
        assertEquals("Gymshark Joggers", newDM.getNames(db.clothesList1)[0]); // ?
    }

    @Test
    public void testGetTags() {
    }

    @Test
    public void testGetImgs() {
    }
}