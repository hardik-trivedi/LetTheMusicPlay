package com.hardiktrivedi.letthemusicplay.search

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.hilt.NetworkModule
import com.hardiktrivedi.letthemusicplay.mockresponsedispatcher.MockServerDispatcher
import com.hardiktrivedi.letthemusicplay.util.*
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
@UninstallModules(NetworkModule::class)
@HiltAndroidTest
class SearchAlbumFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var okHttp: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer().apply {
            dispatcher = MockServerDispatcher().RequestDispatcher()
            start(8080)
        }
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttp))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    //TODO Disable animation
    @Test
    fun albumListIsRendered() {
        launchFragmentInHiltContainer<SearchAlbumFragment> {}
        onView(withId(R.id.actionSearch)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("b"))

        Espresso.closeSoftKeyboard()
        onView(isRoot()).perform(waitForViewToBeVisible(R.id.albumRecyclerView))
        onView(isRoot()).perform(waitForViewToBeVisible(R.id.albumArtistTextView))

        onRecyclerView(R.id.albumRecyclerView) {
            hasViewWithContentDescription(
                0,
                R.id.albumArtImageView,
                "Believe album art image"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) { hasView(0, R.id.albumNameTextView, "Believe") }
        onRecyclerView(R.id.albumRecyclerView) { hasView(0, R.id.albumArtistTextView, "Disturbed") }

        onRecyclerView(R.id.albumRecyclerView) {
            hasViewWithContentDescription(
                1,
                R.id.albumArtImageView,
                "Make Believe album art image"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) { hasView(1, R.id.albumNameTextView, "Make Believe") }

        onRecyclerView(R.id.albumRecyclerView) { hasView(1, R.id.albumArtistTextView, "Weezer") }

        onRecyclerView(R.id.albumRecyclerView) {
            hasViewWithContentDescription(
                2,
                R.id.albumArtImageView,
                "Believe (Deluxe Edition) album art image"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) {
            hasView(
                2,
                R.id.albumNameTextView,
                "Believe (Deluxe Edition)"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) {
            hasView(
                2,
                R.id.albumArtistTextView,
                "Justin Bieber"
            )
        }

        onRecyclerView(R.id.albumRecyclerView) {
            hasViewWithContentDescription(
                3,
                R.id.albumArtImageView,
                "Believe album art image"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) { hasView(3, R.id.albumNameTextView, "Believe") }
        onRecyclerView(R.id.albumRecyclerView) {
            hasView(
                3,
                R.id.albumArtistTextView,
                "Justin Bieber"
            )
        }

        onRecyclerView(R.id.albumRecyclerView) {
            hasViewWithContentDescription(
                4,
                R.id.albumArtImageView,
                "Believe album art image"
            )
        }
        onRecyclerView(R.id.albumRecyclerView) { hasView(4, R.id.albumNameTextView, "Believe") }
        onRecyclerView(R.id.albumRecyclerView) { hasView(4, R.id.albumArtistTextView, "Cher") }

    }

    @Test
    fun invalidSearchValueExceptionIsHandled() {
        launchFragmentInHiltContainer<SearchAlbumFragment> {}
        onView(withId(R.id.actionSearch)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText(" "))

        Espresso.closeSoftKeyboard()
        onView(isRoot()).perform(waitForViewToBeVisible(com.google.android.material.R.id.snackbar_text))
        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Please provide search value")))
    }
}
