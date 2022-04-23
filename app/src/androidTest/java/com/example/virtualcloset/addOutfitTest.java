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
public class addOutfitTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDB() {
        Database database=new Database(false);
        DataManager dm=new DataManager(database);
        UserAccount account=dm.findAccount("user","password");
        //if exist an outfit called NewOutfit remove it.
        Outfit outfit=account.getClosets().get(0).findOutfit("NewOutfit");
        if(outfit!=null){
            account.getClosets().get(0).removeOutfit(outfit);
        }

    }

    @Test
    public void addOutfit() {
        //login default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard()); //close the keyboard after text input
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.navigation_outfits)).perform(click());
        //perform check here
        onView(withId(R.id.outfit_add_button)).perform(click());
        onView(withId(R.id.editOutfit)).perform(typeText("NewOutfit")).perform(closeSoftKeyboard());
        onView(withId(R.id.add_one_outfit)).perform(click());
        //adding to the front or size of Outfit
        onData(anything()).inAdapterView(withId(R.id.gridOutfitList)).atPosition(0)
                .onChildView(withId(R.id.item_name))
                .check(matches(withText("NewOutfit")));

        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }
}
