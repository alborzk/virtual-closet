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
import com.example.virtualcloset.objects.Outfit;
import com.example.virtualcloset.objects.UserAccount;
import com.example.virtualcloset.presentation.MainActivity;
import com.example.virtualcloset.storage.Database;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class changeUserNameTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setupDB() {
        Database database=new Database(false);
        DataManager dm=new DataManager(database);
        UserAccount account=dm.findAccount("Niko","password");
        //if account Niko found change its username/password
        if(account!=null){
            account.setUsername("user");
            account.setPassword("password");
        }
        //login in with default account
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("user"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

        //go to accounts page
        //check if the user name is the corresponding user name
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.nameDisplay)).check(matches(withText("user")));
    }
    @Test
    public void userName(){
        //change userName
        onView(withId(R.id.usernameButton)).perform(click());
        onView(withContentDescription("user name")).perform(typeText("Niko"));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.nameDisplay)).check(matches(withText("Niko")));
        //sign out
        onView(withId(R.id.navigation_accounts)).perform(click());
        onView(withId(R.id.signOutButton)).perform(click());
        //login again with user name : Niko
        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("Niko"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"))
                .perform(closeSoftKeyboard());
        onView(withId(R.id.loginButton)).perform(click());

    }

}
