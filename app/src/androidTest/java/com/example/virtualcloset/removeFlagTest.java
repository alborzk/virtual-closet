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
public class removeFlagTest {
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
    public void removeFlag(){
        //before add a flag
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("Gymshark Joggers")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  Workout  |  ")));

        //select "WorkOut" and remove it
        onView(withId(R.id.editButton)).perform(click());
        onView(withId(R.id.tagSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Workout"))).perform(click());
        onView(withId(R.id.removeTagButton)).perform(click());
        //check the removed tag disappear instantly
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  ")));
        //check again
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.doneButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("Gymshark Joggers")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  Black  |  Pants  |  ")));

    }
}
