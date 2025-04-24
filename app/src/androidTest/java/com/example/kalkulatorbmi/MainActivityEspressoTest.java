package com.example.kalkulatorbmi;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityEspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBMICalculation() {
        // Wprowadź wagę
        onView(withId(R.id.editTextWeight))
                .perform(typeText("70"), closeSoftKeyboard());

        // Wprowadź wzrost
        onView(withId(R.id.editTextHeight))
                .perform(typeText("175"), closeSoftKeyboard());

        // Kliknij przycisk oblicz
        onView(withId(R.id.buttonCalculate))
                .perform(click());

        // Sprawdź czy wynik jest wyświetlany i zawiera oczekiwane BMI (około 22.86)
        onView(withId(R.id.textViewResult))
                .check(matches(withText(containsString("22.86"))));

        // Sprawdź czy status jest wyświetlany i zawiera "optimum"
        onView(withId(R.id.textViewStatus))
                .check(matches(withText(containsString("optimum"))));
    }

    @Test
    public void testEmptyFields() {
        // Kliknij przycisk oblicz bez wprowadzania danych
        onView(withId(R.id.buttonCalculate))
                .perform(click());
    }

    @Test
    public void testNavigationFromMenuToBMI() {
        ActivityScenario.launch(MenuActivity.class);

        // Kliknij przycisk do kalkulatora BMI
        onView(withId(R.id.btnCalculateBMI))
                .perform(click());

        // Sprawdź czy jesteśmy w MainActivity (czy widać elementy kalkulatora BMI)
        onView(withId(R.id.editTextWeight))
                .check(matches(isDisplayed()));

        onView(withId(R.id.editTextHeight))
                .check(matches(isDisplayed()));

        onView(withId(R.id.buttonCalculate))
                .check(matches(isDisplayed()));
    }
}