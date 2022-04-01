package com.example.virtualcloset;


import com.example.virtualcloset.logic.ClosetManagerTest;
import com.example.virtualcloset.logic.DataManagerTest;
import com.example.virtualcloset.logic.GridAdapter2Test;
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
        DataManagerTest.class,
        ClosetManagerTest.class,
        GridAdapter2Test.class

})

public class AllTest {
}
