package com.example.qrgo.IntentTests;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnitRunner;

import com.example.qrgo.HomeActivity;
import com.example.qrgo.MainActivity;
import com.example.qrgo.PlayerActivity;
import com.example.qrgo.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.robotium.solo.Solo;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class B_UserProfileTests extends AndroidJUnitRunner {
    private static Solo solo;
    private static String username;
    private static String imei;
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Runs before all tests and creates solo instance.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // Launch the activity under test
        rule.launchActivity(new Intent());
        // Initialize the solo object
        solo = new Solo(getInstrumentation(), rule.getActivity());
        SharedPreferences sharedPreferences = rule.getActivity().getSharedPreferences("qrgodb", Context.MODE_PRIVATE);
        imei = sharedPreferences.getString("qrgodb", "");

        // Edit the shared preferences using the editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Set the "user" key to null
        editor.remove("imei").commit();
        // Set the "user" key to null
        editor.remove("user").commit();
        // Set the "qrgodb" key to null
        editor.remove("qrgodb").commit();

        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "pm grant com.example.qrgo android.permission.ACCESS_COARSE_LOCATION");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "pm grant com.example.qrgo android.permission.ACCESS_FINE_LOCATION");
        InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                "pm grant com.example.qrgo android.permission.CAMERA");

        // Launch the MainActivity
        solo.assertCurrentActivity("Expected MainActivity", MainActivity.class);
        solo.enterText((EditText) solo.getView(R.id.firstNameEntry), "first");
        solo.enterText((EditText) solo.getView(R.id.lastNameEntry), "last");
        solo.enterText((EditText) solo.getView(R.id.emailEntry), "testemail@gmail.com");
        solo.enterText((EditText) solo.getView(R.id.phoneEntry), "1234567890");
        solo.clickOnView(solo.getView(R.id.register));

        // Wait for home activity to launch
        solo.waitForActivity(HomeActivity.class, 2000);
        solo.assertCurrentActivity("Expected HomeActvity", HomeActivity.class);
        username = sharedPreferences.getString("user", "");

    }

    @Test
    public void testProfileBasicInfo() {
        solo.assertCurrentActivity("Expected HomeActvity", HomeActivity.class);
        // Click on the profile picture
        solo.clickOnView(solo.getView(R.id.main_profile_picture));
        solo.waitForActivity(PlayerActivity.class, 2000);
        solo.assertCurrentActivity("Expected PlayerActivity",PlayerActivity.class);

        assertTrue(solo.waitForText("first last", 1, 2000));
        assertTrue(solo.waitForText("testemail@gmail.com", 1, 2000));

        // Click on the close_button
        solo.clickOnView(solo.getView(R.id.close_button));
        solo.waitForActivity(HomeActivity.class, 2000);
        solo.sleep(2000);
        solo.assertCurrentActivity("Expected HomeActivity",HomeActivity.class);
    }
    @AfterClass
    public static void cleanup() {
        if (imei.equals("") && username.equals("")) {
            Log.d("testSignUpUser", "imei or username is empty");
        } else {
            Log.d("testSignUpUser", "imei: " + imei + " username: " + username);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("Users").document(imei).delete(); // replace with the actual ID used in the tests
            db.collection("Profiles").document(username).delete(); // replace with the actual username used in the tests
            solo.finishOpenedActivities();
        }
    }
}
