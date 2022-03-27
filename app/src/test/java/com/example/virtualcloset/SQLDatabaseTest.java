package com.example.virtualcloset;

import com.example.virtualcloset.storage.SQLDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SQLDatabaseTest {
    SQLDatabase db;

    @Before
    public void setUp() throws Exception{
        db = new SQLDatabase();
    }

    @After
    public void tearDown() throws Exception{
    }

    @Test
    public void connectionTest(){
        assert(db.getConnection());
    }
}
