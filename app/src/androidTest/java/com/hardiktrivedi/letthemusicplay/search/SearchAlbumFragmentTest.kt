package com.hardiktrivedi.letthemusicplay.search

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.hilt.NetworkModule
import com.hardiktrivedi.letthemusicplay.mockresponsedispatcher.MockServerDispatcher
import com.hardiktrivedi.letthemusicplay.util.hasViewWithIdAndContentDescriptionAtPosition
import com.hardiktrivedi.letthemusicplay.util.hasViewWithIdAndTextAtPosition
import com.hardiktrivedi.letthemusicplay.util.launchFragmentInHiltContainer
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
class SearchAlbumFragmentTest {

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

    //TODO Disable animation
    @Test
    fun albumListIsRendered() {
        launchFragmentInHiltContainer<SearchAlbumFragment> {
        }
        onView(withId(R.id.actionSearch)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("b"))

        Espresso.closeSoftKeyboard()
        onView(isRoot()).perform(waitForViewToBeVisible(R.id.albumRecyclerView))

        checkContentDescriptionInRecyclerView(0, R.id.albumArtImageView, "Believe album art image")
        checkContentInRecyclerView(0, R.id.albumNameTextView, "Believe")
        checkContentInRecyclerView(0, R.id.albumArtistTextView, "Disturbed")

        checkContentDescriptionInRecyclerView(1, R.id.albumArtImageView, "Make Believe album art image")
        checkContentInRecyclerView(1, R.id.albumNameTextView, "Make Believe")
        checkContentInRecyclerView(1, R.id.albumArtistTextView, "Weezer")

        checkContentDescriptionInRecyclerView(2, R.id.albumArtImageView, "Believe (Deluxe Edition) album art image")
        checkContentInRecyclerView(2, R.id.albumNameTextView, "Believe (Deluxe Edition)")
        checkContentInRecyclerView(2, R.id.albumArtistTextView, "Justin Bieber")

        checkContentDescriptionInRecyclerView(3, R.id.albumArtImageView, "Believe album art image")
        checkContentInRecyclerView(3, R.id.albumNameTextView, "Believe")
        checkContentInRecyclerView(3, R.id.albumArtistTextView, "Justin Bieber")

        checkContentDescriptionInRecyclerView(4, R.id.albumArtImageView, "Believe album art image")
        checkContentInRecyclerView(4, R.id.albumNameTextView, "Believe")
        checkContentInRecyclerView(4, R.id.albumArtistTextView, "Cher")

    }

    private fun checkContentInRecyclerView(
        position: Int,
        viewId: Int,
        expectedValue: CharSequence
    ) {
        onView(withId(R.id.albumRecyclerView)).check(
            hasViewWithIdAndTextAtPosition(
                position,
                viewId,
                expectedValue
            )
        )
    }

    private fun checkContentDescriptionInRecyclerView(
        position: Int,
        viewId: Int,
        expectedValue: CharSequence
    ) {
        onView(withId(R.id.albumRecyclerView)).check(
            hasViewWithIdAndContentDescriptionAtPosition(
                position,
                viewId,
                expectedValue
            )
        )
    }
}
