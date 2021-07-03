package com.hardiktrivedi.letthemusicplay.data.model

import com.hardiktrivedi.letthemusicplay.util.largeAlbumArtUrl
import junit.framework.Assert.assertEquals
import org.junit.Test

class AlbumTest {

    @Test
    fun `image has large album art`() {
        val images = listOf(
            Image(
                url = "https://lastfm.freetls.fastly.net/i/u/34s/1c8439b16ed4ca4e0bac727e7b325581.png",
                size = "small"
            ),
            Image(
                url = "https://lastfm.freetls.fastly.net/i/u/64s/1c8439b16ed4ca4e0bac727e7b325581.png",
                size = "medium"
            ),
            Image(
                url = "https://lastfm.freetls.fastly.net/i/u/174s/1c8439b16ed4ca4e0bac727e7b325581.png",
                size = "large"
            ),
            Image(
                url = "https://lastfm.freetls.fastly.net/i/u/300x300/1c8439b16ed4ca4e0bac727e7b325581.png",
                size = "extralarge"
            )
        )

        assertEquals(
            "https://lastfm.freetls.fastly.net/i/u/174s/1c8439b16ed4ca4e0bac727e7b325581.png",
            images.largeAlbumArtUrl
        )
    }
}