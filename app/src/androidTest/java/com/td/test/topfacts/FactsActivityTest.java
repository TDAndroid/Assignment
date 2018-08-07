package com.td.test.topfacts;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;

import com.td.test.topfacts.uicomponents.facts.FactsActivity;
import com.td.test.topfacts.util.Util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class FactsActivityTest {

    public Context appTestContext;

    @Rule
    public ActivityTestRule<FactsActivity> activityTestRule =
            new ActivityTestRule<>(FactsActivity.class);

    @Before
    public void initSetup() {
        appTestContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void testActivity() {
        if (!Util.isNetworkConnected(appTestContext)) {
            onView(withText(R.string.app_global_network_message))
                    .inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView()))))
                    .check(matches(isDisplayed()));
        }
    }

    @Test
    public void testActionBarTitle() {
        if (!Util.isNetworkConnected(appTestContext)) {
            onView(withId(R.id.appTitle)).check(matches(withText(R.string.app_name)));
        }
    }

    @Test
    public void testPerformSwipeDown() {
        if (Util.isNetworkConnected(appTestContext)) {
            onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown());
        }
    }

    @Test
    public void testFactsList() {
        onView(withId(R.id.rvFactsList)).check(matches(isDisplayed()));
    }

    @Test
    public void testFactsListRows() {
        if (Util.isNetworkConnected(appTestContext)) {
            RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.rvFactsList);
            int rowCounts = recyclerView.getAdapter().getItemCount();
            assertTrue(rowCounts > 0);
        }
    }
}
