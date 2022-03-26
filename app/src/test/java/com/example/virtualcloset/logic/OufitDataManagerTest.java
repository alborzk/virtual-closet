package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.storage.Database;

import org.junit.Test;

import java.util.ArrayList;

public class OufitDataManagerTest {
    Database db = new Database();
    ArrayList<ClothesItem> clothesItems;
    @Test
    public void getID() {
        OufitDataManager newDM = new OufitDataManager(db);
        assertEquals(2,newDM.getID().length);
        assertEquals(0,newDM.getID()[0]);
        assertEquals(1,newDM.getID()[1]);
    }
}