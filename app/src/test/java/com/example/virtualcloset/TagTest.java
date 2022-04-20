package com.example.virtualcloset;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TagTest {
    /*we have lots of String here
      some edge case with String:
        `blank space
        `Lower/Uppercase
        `
     */
    Tag newTag;
    @Before
    public void setUp() throws Exception {
//        newTag = new Tag("Red", "Colour");
        newTag = new Tag(1, "Red");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void constructorTest(){

        assert ("Red".equals(newTag.getName()));
        //assert ("Colour".equals(newTag.getType()));
    }


    @Test
    public void getID() {
        assertEquals("Tag retrieved should be the same", 1,newTag.getId());
    }

    @Test
    public void setID() {
        newTag.setId(2);
        assertEquals("Tag retrieved should be the same", 2,newTag.getId());
    }

//    @Test
//    public void getType() {
//        assert ("Colour".equals(newTag.getType()));
//    }

    @Test
    public void setName() {
        newTag.setName("Shirt");
        assert ("Shirt".equals(newTag.getName()));
    }

//    @Test
//    public void setType() {
//        newTag.setType("Categories");
//        assertEquals("These must equal", "Categories",newTag.getType());
//    }
}