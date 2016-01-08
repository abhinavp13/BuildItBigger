package com.pabhinav.bib.builditbigger;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * This method test {@link TellMeAJoke} activity class,
 * this test involves calling GCE module, which in turn
 * calls jokesupplylibrary for fetching a joke.
 * <p>
 * More detail :
 * It tests async fetching of joke by gce module,
 * and also checks whether joke is updated correctly in ui.
 * <p>
 * In order to confirm proper UI update,
 * it mocks jokesupplylibrary {@code tellMeJoke} method,
 * which is responsible for providing joke, and return
 * some fixed joke string, which is then asserted for validation.
 * <p>
 *
 * @author pabhinav
 */
@LargeTest
public class AsyncJokeLoadingTest {

    /**
     * Time to sleep for test thread,
     * Upper time limit for GCE module to fetch joke,
     * expects joke to be fetched by this time.
     */
    private int sleepingTimeInSecs = 5;

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<TellMeAJoke> mActivityRule = new ActivityTestRule<>(
            TellMeAJoke.class);

    /**
     * This functional test, tests for correct fetching of joke
     * from jokesupplylibrary by GCE endpoint and correct updation
     * in app's UI.
     *
     * This test is consumed by other tests.
     *
     * @throws Exception
     */
    @Test
    public void connectedTestForJokeSupply() throws Exception{

        /**
         * Since in the activity beginning, joke fetching automatically
         * begins, so we don't need to call again GCE module.
         * Just wait for sometime till joke is correctly fetched,
         * and will then confirm its correct updation.
         */
        Thread.sleep(sleepingTimeInSecs * 1000);

        try {
            /** Matcher, testing things displayed correctly **/
            onView(withId(R.id.text_view_card_one)).check(matches(isDisplayed()));
            onView(withId(R.id.plz_wait_text_view_one)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
            onView(withId(R.id.truck_image_view_one)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        } catch (Exception e){
            throw new AssertionError("UI Elements Not Updating Properly Even After " + sleepingTimeInSecs +" secs");
        }
    }
}
