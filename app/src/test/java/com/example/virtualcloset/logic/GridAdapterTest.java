package com.example.virtualcloset.logic;

import static org.junit.Assert.*;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridAdapterTest {
    Context context;
    String[] clothesNames;
    int image;
    GridAdapter grid1;

    @Before
    public void setUp() throws Exception {
        clothesNames = new String[10];
        grid1 = new GridAdapter(context, clothesNames, image);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getCount() {
        assertEquals("the length should be 10", 10, grid1.getCount());
    }

    @Test
    public void getItem() {
        int randomId = (int) (Math.random() * 10);
        assertEquals("return object null",null, grid1.getItem(randomId));
    }

    @Test
    public void getItemId() {
        int randomId = (int) (Math.random() * 10);
        assertEquals("return itemId",0, grid1.getItemId(randomId));
    }
}