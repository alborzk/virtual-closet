package com.example.virtualcloset;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.virtualcloset.logic.DataManager;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.presentation.MainActivity;
import com.example.virtualcloset.storage.Database;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class addClothesTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    int numClothes;
    @Before
    public void setupDB() {
        Database database=new Database(false);
        DataManager dm=new DataManager(database);
        UserAccount account=dm.findAccount("user","password");
        numClothes=account.getClosets().get(0).getNumClothes();
        //login default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.navigation_clothes)).perform(click());
    }
    @Test
    public void addClothes(){
        onView(withId(R.id.addItemButton)).perform(click());
        onView(withId(R.id.selectImageButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.addImageGrid)).atPosition(5).perform(click());
        onView(withId(R.id.editTextAdd)).perform(typeText("NewClothes")).perform(closeSoftKeyboard());

        onView(withId(R.id.editTags)).perform(typeText("TagsforNewClothes")).perform(closeSoftKeyboard());
        onView(withId(R.id.addTagButton)).perform(click());
        onView(withId(R.id.doneAddButton)).perform(click());

        //click the added item on GridView
        //check the details of item
        onData(anything()).inAdapterView(withId(R.id.gridView)).atPosition(numClothes).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("NewClothes")));
        onView(withId(R.id.tagDisplay)).check(matches(withText("|  TagsforNewClothes  |  ")));
        onView(withId(R.id.doneButton)).perform(click());
        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
    }
}
