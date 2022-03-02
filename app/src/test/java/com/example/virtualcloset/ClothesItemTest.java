package com.example.virtualcloset;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ClothesItemTest {

    private ArrayList<Tag> tags;
    private Tag tag1;
    private ClothesItem clothesItem;

    @Before
    public void setUp() throws IOException {
        tags = new ArrayList<Tag>();
        tags.add(tag1);
        this.clothesItem = new ClothesItem("blue_skirt", tags, "blue_skirt.png");
    }

    // Test Getters
    @Test
    public void testGetName() {
        assertEquals("name should be equal", "blue_skirt", clothesItem.getName());
    }

    @Test
    public void testGetTags() {
        assertEquals("tags should be equal", tags, clothesItem.getTags());
    }

    @Test
    public void testGetImg() {
        assertEquals("image should be equal", "blue_skirt.png", clothesItem.getImg());
    }

    // Test Setters
    @Test
    public void testSetName() {
        clothesItem.setName("black_shoes");
        assertEquals("name should be equal", "black_shoes", clothesItem.getName());
    }

    @Test
    public void testSetTags() {
        Tag tag2;
        ArrayList<Tag> tags2 = new ArrayList<Tag>();
        tags2.add(tag2);
        clothesItem.setTags(tags2);
        assertEquals("tags should be equal", tags2, clothesItem.getTags());
    }

    @Test
    public void testSetImg() {
        clothesItem.setImg("black_shoes.png");
        assertEquals("image should be equal", "black_shoes.png", clothesItem.getImg());
    }
    
    // Test Methods
    @Test
    public void testAddTags() { // ..?
        Tag tag3;
        assertEquals("should add a tag", true, cothesItem.addTag(tag3));
    }

    @After
    public void tearDown() {
    }
}