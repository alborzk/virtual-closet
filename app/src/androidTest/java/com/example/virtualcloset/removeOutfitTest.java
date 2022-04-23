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

import com.example.virtualcloset.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class removeOutfitTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDB() {
        //here we will delete the account that will be created
        //make sure we have Gymshark Joggers in our closet at position 0
        //flag

        //login default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard()); //close the keyboard after text input
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.navigation_outfits)).perform(click());

    }

    @Test
    public void removeOutfit(){
        //before remove
        onData(anything()).inAdapterView(withId(R.id.gridOutfitList)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("Work")));
        //after removed "Work"
        onView(withId(R.id.outfit_delete_button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridOutfitList)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridOutfitList)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("Casual")));
        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }

}
