package br.com.dagnese.leituradeetiquetas;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
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

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReleaseTeste01 {

    private Context instrumentationContext;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<CameraActivity> cameraActivity = new ActivityTestRule<>(CameraActivity.class);

    @Rule
    public IntentsTestRule<CameraActivity> cameraActivityIntentsTestRule = new IntentsTestRule<>(CameraActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule = GrantPermissionRule.grant("android.permission.CAMERA");

    @Test
    public void releaseTeste01() {

        ///
        ////
        //// VER ESSE CARA
        //// https://stackoverflow.com/questions/13042015/testing-onactivityresult
        /////

        pressBack();
        instrumentationContext = InstrumentationRegistry.getContext();
        MainActivity mainActivityactivity = mActivityTestRule.getActivity();
        Intent resultData = new Intent();
        Instrumentation.ActivityResult result;
        resultData.putExtra("tipo", "0");
        resultData.putExtra("leitura", "57105405");
        result =  new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(hasComponent(CameraActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));
        cameraActivityIntentsTestRule.getActivity().startActivityForResult(new Intent(instrumentationContext, CameraActivity.class), 200);

        resultData = new Intent();
        resultData.putExtra("tipo", "1");
        resultData.putExtra("leitura", "227101.60.0");
        result =  new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
        intending(hasComponent(CameraActivity.class.getName())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData));
        cameraActivityIntentsTestRule.getActivity().startActivityForResult(new Intent(instrumentationContext, CameraActivity.class), 200);

        ViewInteraction editText = onView(
                allOf(withId(R.id.qtdApontar), withText("60"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed())
                );

        editText.perform(replaceText("25"));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.qtdApontar), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        editText3.perform(closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.qtdApontar), withText("25"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                6),
                        isDisplayed()));
        editText4.perform(pressImeActionButton());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.apontarMais), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                8),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.apontarMais), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                8),
                        isDisplayed()));
        button3.perform(click());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.apontarMais), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                8),
                        isDisplayed()));
        button4.perform(click());

        ViewInteraction button5 = onView(
                allOf(withId(R.id.apontarMais), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                8),
                        isDisplayed()));
        button5.perform(click());

        ViewInteraction button6 = onView(
                allOf(withId(R.id.apontarMais), withText("+"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                8),
                        isDisplayed()));
        button6.perform(click());

        ViewInteraction button7 = onView(
                allOf(withId(R.id.enviar), withText("Enviar"),
                        childAtPosition(
                                allOf(withId(R.id.ConstraintLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                9),
                        isDisplayed()));
        button7.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
