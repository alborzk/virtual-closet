package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridAdapter2Test {

    Context context;
    String[] outfitNames;
    int[] image;
    GridAdapter2 grid2;

    @Before
    public void setUp() throws Exception {
        outfitNames = new String[10];
        grid2 = new GridAdapter2(context, outfitNames);
        assertNotNull(grid2);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCount() {
        assertEquals("the length should be 10", 10, grid2.getCount());
    }

    @Test
    public void getItem() {
        int randomId = (int) (Math.random() * 10);
        assertEquals("return object null",null, grid2.getItem(randomId));
    }

    @Test
    public void getItemId() {
        int randomId = (int) (Math.random() * 10);
        assertEquals("return itemId",0, grid2.getItemId(randomId));
    }
}