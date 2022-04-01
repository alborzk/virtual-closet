package com.example.virtualcloset;

import com.example.virtualcloset.logic.ClosetManagerTest;
import com.example.virtualcloset.logic.DataManagerTest;
import com.example.virtualcloset.logic.GridAdapterTest;
import com.example.virtualcloset.logic.DataManagerTest;
import com.example.virtualcloset.storage.SQLDatabaseTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClosetTest.class,
        ClothesItemTest.class,
        OutfitTest.class,
        TagTest.class,
        UserAccountTest.class,
        GridAdapterTest.class,
        DataManagerTest.class,
        ClosetManagerTest.class,
        SQLDatabaseTest.class
})

public class AllTest {
}
