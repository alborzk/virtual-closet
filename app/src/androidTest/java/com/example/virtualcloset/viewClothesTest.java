package com.example.virtualcloset;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.anything;

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
public class viewClothesTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDB() {
        //here we will delete the account that will be created
        //login default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.navigation_clothes)).perform(click());

        //make sure we have Gymshark Joggers in our closet
    }

    @Test
    public void viewClothes() {
        //check the first item on GridView
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("Gymshark Joggers")));
        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }

    @Test
    public void viewClothesDetails() {
        //click the first item on GridView
        //check the details of item
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("Gymshark Joggers")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  Workout  |  ")));
        onView(withId(R.id.doneButton)).perform(click());
        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }
}
