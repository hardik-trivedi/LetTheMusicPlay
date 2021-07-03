package com.hardiktrivedi.letthemusicplay.util

import com.hardiktrivedi.letthemusicplay.data.model.Image

val List<Image>.largeAlbumArtUrl: String
    get() {
        return this.filter {
            it.size == "large"
        }.map {
            it.url
        }.first()
    }