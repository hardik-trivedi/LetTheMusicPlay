package com.hardiktrivedi.letthemusicplay.mockresponsedispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when {
                request.requestUrl?.query?.contains("album.search") == true -> {
                    MockResponse().setResponseCode(200)
                        .setBody(getJsonContent("album_list_success.json"))
                }
                request.requestUrl?.query?.contains("album.getinfo") == true -> {
                    MockResponse().setResponseCode(200)
                        .setBody(getJsonContent("album_detail_success.json"))
                }
                else -> {
                    MockResponse().setResponseCode(400)
                }
            }
        }
    }

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}