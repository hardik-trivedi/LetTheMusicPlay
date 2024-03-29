package com.hardiktrivedi.letthemusicplay.detail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.hilt.NetworkModule
import com.hardiktrivedi.letthemusicplay.mockresponsedispatcher.MockServerDispatcher
import com.hardiktrivedi.letthemusicplay.util.hasView
import com.hardiktrivedi.letthemusicplay.util.launchFragmentInHiltContainer
import com.hardiktrivedi.letthemusicplay.util.onRecyclerView
import com.hardiktrivedi.letthemusicplay.util.waitForViewToBeVisible
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
class AlbumDetailFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var okHttp: OkHttpClient

    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttp))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun albumDetailIsDisplayed() {
        val args = Bundle().apply {
            putString("artist", "Frank Ocean")
            putString("album", "Blonde")
        }
        launchFragmentInHiltContainer<AlbumDetailFragment>(fragmentArgs = args) {}
        onView(ViewMatchers.isRoot())
            .perform(waitForViewToBeVisible(R.id.trackRecyclerView))
        onView(ViewMatchers.isRoot())
            .perform(waitForViewToBeVisible(R.id.titleTextView))


        onView(withId(R.id.albumNameTextView)).check(matches(withText("Blonde")))
        onView(withId(R.id.albumArtistTextView)).check(matches(withText("Frank Ocean")))
        onView(withId(R.id.albumPublishedDate)).check(matches(withText("03 Mar 2017, 18:40")))
        onView(withId(R.id.listenerCountTextView)).check(matches(withText("743.2 K")))
        onView(withId(R.id.playCountTextView)).check(matches(withText("65.5 M")))

        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.titleTextView, "Nikes") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.artistTextView, "Frank Ocean") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.durationTextView, "05:14") }

        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.titleTextView, "Ivy") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.artistTextView, "Frank Ocean") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.durationTextView, "04:09") }

        onRecyclerView(R.id.trackRecyclerView) { hasView(2, R.id.titleTextView, "Pink + White") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(2, R.id.artistTextView, "Frank Ocean") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(2, R.id.durationTextView, "03:04") }
    }

    @Test
    fun albumDetailIsDisplayedWithoutPublishedDateAndSummary() {
        val args = Bundle().apply {
            putString("artist", "Weezer")
            putString("album", "Make")
        }
        launchFragmentInHiltContainer<AlbumDetailFragment>(fragmentArgs = args) {}
        onView(ViewMatchers.isRoot())
            .perform(waitForViewToBeVisible(R.id.trackRecyclerView))
        onView(ViewMatchers.isRoot())
            .perform(waitForViewToBeVisible(R.id.titleTextView))


        onView(withId(R.id.albumNameTextView)).check(matches(withText("Make Believe")))
        onView(withId(R.id.albumArtistTextView)).check(matches(withText("Weezer")))
        onView(withId(R.id.albumPublishedDate)).check(matches(withText("")))
        onView(withId(R.id.albumSummaryTextView)).check(matches(withText("")))
        onView(withId(R.id.listenerCountTextView)).check(matches(withText("743.2 K")))
        onView(withId(R.id.playCountTextView)).check(matches(withText("65.5 M")))

        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.titleTextView, "Beverly Hills") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.artistTextView, "Weezer") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(0, R.id.durationTextView, "05:14") }

        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.titleTextView, "Perfect Situation") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.artistTextView, "Weezer") }
        onRecyclerView(R.id.trackRecyclerView) { hasView(1, R.id.durationTextView, "04:09") }
    }
}