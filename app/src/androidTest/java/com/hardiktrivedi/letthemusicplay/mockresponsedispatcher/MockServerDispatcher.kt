package com.hardiktrivedi.letthemusicplay.mockresponsedispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.io.InputStreamReader

class MockServerDispatcher {
    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when {
                request.requestUrl?.query?.contains("believe") == true -> {
                    MockResponse().setResponseCode(200)
                        .setBody(getJsonContent("album_list_success.json"))
                }
                request.requestUrl?.query?.contains("Error") == true -> {
                    MockResponse().setResponseCode(400)
                        .setBody(getJsonContent("album_list_error.json"))
                }
                isAlbumDetailSuccess(request) -> {
                    MockResponse().setResponseCode(200)
                        .setBody(getJsonContent("album_detail_success.json"))
                }
                isAlbumDetailWithNoWikiNoPublishDate(request) -> {
                    MockResponse().setResponseCode(200)
                        .setBody(getJsonContent("album_detail_with_no_wiki_no_published_date.json"))
                }
                else -> {
                    MockResponse().setResponseCode(400)
                }
            }
        }
    }

    private fun isAlbumDetailSuccess(request: RecordedRequest): Boolean {
        return request.requestUrl?.query?.contains("album.getinfo") == true && request.requestUrl?.query?.contains(
            "Believe"
        ) == true
    }

    private fun isAlbumDetailWithNoWikiNoPublishDate(request: RecordedRequest): Boolean {
        return request.requestUrl?.query?.contains("album.getinfo") == true && request.requestUrl?.query?.contains(
            "Weezer"
        ) == true
    }

    private fun getJsonContent(fileName: String): String {
        return InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(fileName)).use { it.readText() }
    }
}