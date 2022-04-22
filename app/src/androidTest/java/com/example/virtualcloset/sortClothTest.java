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
        onView(withId(R.id.tagFilter)).perform(click());

    }
}