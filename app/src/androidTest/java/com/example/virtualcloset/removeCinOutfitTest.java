package com.example.virtualcloset;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.presentation.MainActivity;
import com.example.virtualcloset.storage.Database;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class removeCinOutfitTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    @Before
    public void setupDB() {
        //login default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.navigation_outfits)).perform(click());

    }
    @Test
    public void deleteClothesInOutfit(){
        //before deletion
        onData(anything()).inAdapterView(withId(R.id.gridOutfitList)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridview2)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("White H&M Tee")));


        //after deletion
        onView(withId(R.id.outfit_deleItem)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridview2)).atPosition(0).perform(click());
        onView(withId(R.id.outfitItem_back)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridview2)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("Blue Levi's Jeans")));
        //sign out
        onView(withId(R.id.done_outfit)).perform(click());
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());



    }
}
