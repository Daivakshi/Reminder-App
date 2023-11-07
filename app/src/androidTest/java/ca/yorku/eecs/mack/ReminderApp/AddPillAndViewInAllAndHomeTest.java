package ca.yorku.eecs.mack.ReminderApp;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddPillAndViewInAllAndHomeTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void addPillAndViewInAllAndHome() {

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withId(R.id.addPillBtn), withContentDescription("add pills button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.pillName),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                7),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Test 1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.pillAmount),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.pillDosage),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                4),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.pillTime), withText("Select Time"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                5),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                2),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.pillMonday), withText("Monday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                9),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.pillTuesday), withText("Tuesday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                1),
                        isDisplayed()));
        materialCheckBox2.perform(click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.pillWednesday), withText("Wednesday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                11),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.pillThursday), withText("Thursday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                0),
                        isDisplayed()));
        materialCheckBox4.perform(click());

        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.pillFriday), withText("Friday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                3),
                        isDisplayed()));
        materialCheckBox5.perform(click());

        ViewInteraction materialCheckBox6 = onView(
                allOf(withId(R.id.pillSaturday), withText("Saturday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                10),
                        isDisplayed()));
        materialCheckBox6.perform(click());

        ViewInteraction materialCheckBox7 = onView(
                allOf(withId(R.id.pillSunday), withText("Sunday"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                2),
                        isDisplayed()));
        materialCheckBox7.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.createPillButton), withText("Create Pill"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.pillButton),
                                        0),
                                8),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("View Medicine"),
                        withParent(allOf(withId(com.google.android.material.R.id.action_bar),
                                withParent(withId(com.google.android.material.R.id.action_bar_container)))),
                        isDisplayed()));
        textView.check(matches(withText("View Medicine")));

        ViewInteraction button = onView(
                allOf(withId(R.id.createPillButton), withText("EDIT PILLS"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction listView = onView(
                allOf(withId(R.id.viewPillsListView),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Test 1\n Amount: 1.0\n Dosage: 10.0\n Days in the week to take PiLL:   \n monday. tuesday. wednesday. thursday. friday. saturday. sunday."),
                        withParent(allOf(withId(R.id.viewPillsListView),
                                withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class)))),
                        isDisplayed()));
        textView2.check(matches(withText("Test 1  Amount: 1.0  Dosage: 10.0  Days in the week to take PiLL:     monday. tuesday. wednesday. thursday. friday. saturday. sunday.")));

        pressBack();

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.cardView),
                        withParent(withParent(withId(R.id.todayRecycler))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.pill_time), withText("11:00PM"),
                        withParent(withParent(withId(R.id.cardView))),
                        isDisplayed()));
        textView3.check(matches(withText("11:00PM")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.pill_name), withText("Take 1.0 or 10.0mg of Test 1"),
                        withParent(withParent(withId(R.id.cardView))),
                        isDisplayed()));
        textView4.check(matches(withText("Take 1.0 or 10.0mg of Test 1")));
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
