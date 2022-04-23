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
public class sortClothTest {
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
    public void sortClothes(){
        //select Pants
        //check the first item of selected Tag
        onView(withId(R.id.tagFilter)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Pants"))).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("Gymshark Joggers")));

        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }
}
