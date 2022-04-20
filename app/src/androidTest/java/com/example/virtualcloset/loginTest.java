package com.example.virtualcloset;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import com.example.virtualcloset.presentation.LoginActivity;
import com.example.virtualcloset.presentation.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class loginTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    //    public ActivityScenarioRule<LoginActivity> activityRule =new ActivityScenarioRule<>(LoginActivity.class);
    @Before
    public void setupDB() {
        //here we will delete the account that will be created
        //delete "NewAccount" with password "PASSWORD"
    }

    @Test
    public void loginWithDefault() {
        //login in with default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

//      go to accounts page
        //check if the user name is the corresponding user name
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("user")));

    }

    @Test
    public void signUp() {
        //login in with default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("NewAccount"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("PASSWORD"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.signupButton)).perform(click());

        //go to accounts page
        //check if the user name is the corresponding user name
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("NewAccount")));
        onView(withId(R.id.numClothes)).check(matches(withText("Clothes: 0")));
        onView(withId(R.id.numOutfits)).check(matches(withText("Outfits: 0")));

    }

}
