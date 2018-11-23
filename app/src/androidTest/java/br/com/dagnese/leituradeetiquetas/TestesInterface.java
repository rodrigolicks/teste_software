package br.com.dagnese.leituradeetiquetas;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Camera;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestesInterface {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<CameraActivity> cameraActivityIntentsTestRule = new IntentsTestRule<>(CameraActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule = GrantPermissionRule.grant("android.permission.CAMERA");

    @Test
    public void novoTesteLeituraChapa() {
        MainActivity mainActivityactivity = mActivityTestRule.getActivity();
        Intent resultData = new Intent();
        resultData.putExtra("tipo", "0");
        resultData.putExtra("leitura", "57105405");
        intending(hasComponent(CameraActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));
    }

    @Test
    public void testaLeituraChapa() {
        /*
        CameraActivity cameraActivity = cameraActivityIntentsTestRule.getActivity();

        // Faz o mock do resultado da câmera
        Intent resultData = new Intent();
        resultData.putExtra("tipo", "0");
        resultData.putExtra("leitura", "57105405");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(ChildActivity.class.getName(), activityResult , true);
        */
    }




    // Tentativa de teste com instrumentação do espresso blablblalba
    @Test
    public void testeLeituraSequencial() {
        //Intents.init();
        Matcher<Intent> matcher = hasComponent(CameraActivity.class.getName());
        Intent resultData = new Intent();

        resultData.putExtra("tipo", "0");
        resultData.putExtra("leitura", "57105405");
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(matcher).respondWith(result);

        //intended(matcher);

        /*
        resultData.putExtra("tipo", "0");
        resultData.putExtra("leitura", "227102.2.0");
        result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        */
        Intents.release();
    }
}
