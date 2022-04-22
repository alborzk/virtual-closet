package com.example.virtualcloset;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.anything;

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
public class addFlagTest {
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
        onView(withId(R.id.navigation_clothes)).perform(click());

    }
    @Test
    public void addFlag(){
        //before add a flag
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("Gymshark Joggers")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  Workout  |  ")));

        //add a flag
        onView(withId(R.id.editButton)).perform(click());
        onView(withId(R.id.editTags)).perform(typeText("NewTag")).perform(closeSoftKeyboard());
        //check the added tag show up instantly
        onView(withId(R.id.addButton)).perform(click());
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  Workout  |  NewTag  |  ")));
        //check again
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.doneButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("Gymshark Joggers")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  Workout  |  NewTag  |  ")));
    }
}
