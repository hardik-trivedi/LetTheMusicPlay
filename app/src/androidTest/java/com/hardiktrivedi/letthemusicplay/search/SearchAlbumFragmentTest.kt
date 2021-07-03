package com.hardiktrivedi.letthemusicplay.search

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hardiktrivedi.letthemusicplay.R
import com.hardiktrivedi.letthemusicplay.hilt.NetworkModule
import com.hardiktrivedi.letthemusicplay.mockresponsedispatcher.MockServerDispatcher
import com.hardiktrivedi.letthemusicplay.util.launchFragmentInHiltContainer
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

    @Test
    fun AlbumListIsRendered() {
        launchFragmentInHiltContainer<SearchAlbumFragment> {
        }
        onView(withId(R.id.actionSearch)).perform(click())
        onView(withId(androidx.appcompat.R.id.search_src_text)).perform(typeText("b"))

    }
}
