package com.matiaslev.ualamovies.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.matiaslev.ualamovies.di.testModule
import com.matiaslev.ualamovies.di.testModuleError
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4::class)
@LargeTest
class BooksActivityBookErrorTest : KoinTest {

    @get:Rule
    val rule = object : ActivityTestRule<BooksActivity>(BooksActivity::class.java) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            loadKoinModules(testModuleError)
        }
    }

    @Test
    fun ups() {
        onView(withText("ups!")).check(matches(isDisplayed()))
    }

    @After
    fun unloadModule() {
        unloadKoinModules(testModuleError)
    }
}