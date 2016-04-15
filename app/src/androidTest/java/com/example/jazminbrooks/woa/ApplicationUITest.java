package com.example.jazminbrooks.woa;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.example.jazminbrooks.woa.Data.WorkoutContent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationUITest extends ActivityInstrumentationTestCase2<Login> {

    private String mTestEmail = "Email";
    private String mTestUsername = "Test";
    private String mTestPassword = "password";
    private SharedPreferences.Editor editor;

    public ApplicationUITest() {
        super(Login.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        editor = getInstrumentation().getTargetContext().getSharedPreferences("com.example.jazminbrooks.woa", Context.MODE_PRIVATE).edit();

        editor.putString("email", mTestEmail);
        editor.putString("username", mTestUsername);
        editor.putString("password", Login.generate_hashed_password(mTestPassword));
    }

        @Rule
    public ActivityTestRule<Login> mActivityRule = new ActivityTestRule<>(
            Login.class);


    @Test
    public void TestLoginTextEditActivity() {
        // Type text and then press the button.
        onView(withId(R.id.emailField))
                .perform(typeText(mTestEmail), closeSoftKeyboard());

        onView(withId(R.id.passwordField))
                .perform(typeText(mTestPassword), closeSoftKeyboard());



        // Check that the text was changed.
        onView(withId(R.id.emailField))
                .check(matches(withText(mTestEmail)));

        onView(withId(R.id.passwordField))
                .check(matches(withText(mTestPassword)));
    }
}
