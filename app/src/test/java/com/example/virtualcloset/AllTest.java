package com.example.virtualcloset;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClosetTest.class,
        ClothesItemTest.class,
        OutfitTest.class,
        TagTest.class,
        UserAccountTest.class
})

public class AllTest {
}
