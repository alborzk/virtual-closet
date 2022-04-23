//package com.example.virtualcloset.logic;
//
//import static org.junit.Assert.*;
//
//import com.example.virtualcloset.objects.ClothesItem;
//import com.example.virtualcloset.objects.Outfit;
//import com.example.virtualcloset.R;
//import com.example.virtualcloset.objects.Tag;
//import com.example.virtualcloset.storage.Database;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class OutfitDataManagerTest {
//    Database db = new Database(false);
//    ArrayList<Outfit> outfits;
//    ArrayList<ClothesItem> clothesList1=null;
//    OutfitDataManager dm;
//
//    @Before
//    public void setUp() throws Exception {
//        outfits = new ArrayList<Outfit>();
//        dm=new OutfitDataManager(db);
//        clothesList1=new ArrayList<ClothesItem>();
//        ArrayList<Tag> tags1 = new ArrayList<Tag>();
////        tags1.add(new Tag("Black","Color"));
////        tags1.add(new Tag("Pants","Type"));
////        tags1.add(new Tag("Workout","Setting"));
//        clothesList1.add(new ClothesItem(1, "Gymshark Joggers", tags1, R.drawable.placeholder_0));
//        ArrayList<Tag> tags2 = new ArrayList<Tag>();
////        tags2.add(new Tag("Blue","Color"));
////        tags2.add(new Tag("Pants","Type"));
//        clothesList1.add(new ClothesItem(2, "Blue Levi's Jeans", tags2, R.drawable.placeholder_1));
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//    @Test
//    public void constructorTest() {
//        assertNotNull(dm);
//        assertEquals("the first outfit's name should match", "WORK", dm.getOutfitName()[0]);
//        assertEquals("the first outfit ID should match", 0, dm.getID()[0]);
//    }
//    @Test
//    public void getOutfitName() {
//        assertEquals("the first outfit's name should match", "CASUAL", dm.getOutfitName()[1]);
//    }
//
//    @Test
//    public void testGetID() {
//        assertEquals("the length of getID should be 2",2,dm.getID().length);
//        assertEquals("the first outfit ID should match", 1, dm.getID()[1]);
//    }
//
//    @Test
//    public void getNames() {
//        assertEquals("the first cloth's name should match", "Gymshark Joggers", dm.getNames(clothesList1)[0]);
//        assertEquals("the second cloth's name should match", "Blue Levi's Jeans", dm.getNames(clothesList1)[0]);
//    }
//
//    @Test
//    public void getTags() {
//        assertEquals("the first cloth's tag should match", "|  Black  |  Pants  |  Workout  |  ", dm.getTags(clothesList1)[0]);
//    }
//
//    @Test
//    public void getImgs() {
//        assertEquals("the first cloth's img should match", R.drawable.placeholder_0, dm.getImgs(clothesList1)[0]);
//    }
//}