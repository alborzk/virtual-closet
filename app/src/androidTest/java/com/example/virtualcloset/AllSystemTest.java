package com.example.virtualcloset;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        //need to disable three types of animation scale before running the test
        addFlagTest.class,
        addOutfitTest.class,
        favouriteTest.class,
        loginTest.class,
        removeFlagTest.class,
        removeOutfitTest.class,
        sortClothTest.class,
        viewClothesTest.class,
        viewOutfitTest.class,
        addClothesTest.class,
        addCinOutfitTest.class,
        deleteClothesTest.class,
        removeCinOutfitTest.class
})

public class AllSystemTest {
}
