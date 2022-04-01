package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import com.example.virtualcloset.Closet;
import com.example.virtualcloset.ClothesItem;
import com.example.virtualcloset.Outfit;
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
    ArrayList<Outfit> outfits;
    ArrayList<ClothesItem> clothesList1 = null;
    ClosetManager cm;

    @Before
    public void setUp() throws Exception {
        clothesItems = new ArrayList<ClothesItem>();
        outfits = new ArrayList<Outfit>();
        c = new Closet(0, clothesItems, outfits);
        cm = new ClosetManager(c);
        clothesList1=new ArrayList<ClothesItem>();

        ArrayList<Tag> tags1 = new ArrayList<Tag>();
        tags1.add(new Tag(10, "Black"));
        tags1.add(new Tag(11, "Pants"));
        tags1.add(new Tag(12, "Workout"));
        c.addClothesItem(new ClothesItem(1, "Gymshark Joggers", tags1, R.drawable.placeholder_0));
        clothesList1.add(new ClothesItem(1, "Gymshark Joggers", tags1, R.drawable.placeholder_0));

        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(new Tag(20, "Blue"));
        tags2.add(new Tag(21, "Pants"));
        c.addClothesItem(new ClothesItem(2, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));
        clothesList1.add(new ClothesItem(2, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));
        c.addOutfit(new Outfit(4, "WORK", clothesList1));
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void constructorTest() {
        assertNotNull(cm);
    }
    @Test
    public void getOutfitName() {
        String[] cmOutfitsNames = cm.getOutfitsNames();
        assertEquals("the retrieved String[] and the actual arraylist should be same size", cmOutfitsNames.length, outfits.size());
        assertEquals("the first outfit's name should match", outfits.get(0).getName(), cmOutfitsNames[0]);
    }

    @Test
    public void getOutfitImg() {
        int[] cmOutfitsImgs = cm.getClothesImgs();
        assertEquals("the etrieved int[] and the actual arraylist should be same size", cmOutfitsImgs.length, outfits.size());
        assertEquals("the first cloth's img should match", R.drawable.placeholder_outfit, cmOutfitsImgs[0]);
    }

    @Test
    public void getClothesNames() {
        String[] cmClothesNames = cm.getClothesNames();
        assertEquals("the retrieved String[] and the actual arraylist should be same size", cmClothesNames.length, clothesItems.size());
        assertEquals("the first clothe's name should match", clothesItems.get(0).getName(), cmClothesNames[0]);
    }

    @Test
    public void getClothesImgs() {
        int[] cmClothesImgs = cm.getClothesImgs();
        assertEquals("the etrieved int[] and the actual arraylist should be same size", cmClothesImgs.length, clothesItems.size());
        assertEquals("the first cloth's img should match", R.drawable.placeholder_0, cmClothesImgs[0]);
    }
}