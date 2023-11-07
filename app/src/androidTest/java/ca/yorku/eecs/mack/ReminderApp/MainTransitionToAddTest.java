package ca.yorku.eecs.mack.ReminderApp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainTransitionToAddTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainTransitionToAddTest() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.addPillBtn), withContentDescription("add pills button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageButton.perform(click());


        ViewInteraction textView = onView(
                allOf(withText("Add Medicine"),
                        withParent(allOf(withId(com.google.android.material.R.id.action_bar),
                                withParent(withId(com.google.android.material.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("Add Medicine")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.pillName), withHint("Pill Name"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.pillAmount), withHint("Amount"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.pillDosage), withHint("Dosage (mg)"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.pillTime), withText("Select Time"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.textView11), withText("Select Days"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        textView2.check(matches(withText("Select Days")));

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.pillMonday), withText("Monday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox.check(matches(isDisplayed()));

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.pillTuesday), withText("Tuesday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox2.check(matches(isDisplayed()));

        ViewInteraction checkBox3 = onView(
                allOf(withId(R.id.pillWednesday), withText("Wednesday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox3.check(matches(isDisplayed()));

        ViewInteraction checkBox4 = onView(
                allOf(withId(R.id.pillThursday), withText("Thursday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox4.check(matches(isDisplayed()));

        ViewInteraction checkBox5 = onView(
                allOf(withId(R.id.pillFriday), withText("Friday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox5.check(matches(isDisplayed()));

        ViewInteraction checkBox6 = onView(
                allOf(withId(R.id.pillSaturday), withText("Saturday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox6.check(matches(isDisplayed()));

        ViewInteraction checkBox7 = onView(
                allOf(withId(R.id.pillSunday), withText("Sunday"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        checkBox7.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.createPillButton), withText("CREATE PILL"),
                        withParent(withParent(withId(R.id.pillButton))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
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
