package com.example.virtualcloset;


import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.logic.DataManagerTest;
import com.example.virtualcloset.logic.GridAdapterTest;

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
        DataManagerTest.class
})

public class AllTest {
}
