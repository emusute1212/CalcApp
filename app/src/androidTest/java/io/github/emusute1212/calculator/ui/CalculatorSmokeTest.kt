package io.github.emusute1212.calculator.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.emusute1212.calculator.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorSmokeTest {
    @get:Rule
    val compose = createAndroidComposeRule<MainActivity>()

    @Test
    fun calculationSurvivesLicenseNavigation() {
        for (key in listOf("7", "+", "5", "=")) {
            compose.onNodeWithText(key).assertIsDisplayed().performClick()
        }
        compose.waitForIdle()
        // The result uses a TextView hosted by AndroidView.
        onView(withText("12")).check(matches(isDisplayed()))

        val menu = compose.activity.getString(R.string.open_menu)
        val license = compose.activity.getString(R.string.open_source_license)
        val back = compose.activity.getString(R.string.navigate_back)
        compose.onNodeWithContentDescription(menu).assertIsDisplayed().performClick()
        compose.onNodeWithText(license).assertIsDisplayed().performClick()
        compose.onNodeWithContentDescription(back).assertIsDisplayed().performClick()

        compose.onNodeWithContentDescription(menu).assertIsDisplayed()
        compose.waitForIdle()
        onView(withText("12")).check(matches(isDisplayed()))
    }
}
