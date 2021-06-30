package com.hardiktrivedi.letthemusicplay.data.model

import com.google.gson.annotations.SerializedName

data class AlbumSearchResponse(val results: Results)

data class Results(
    @SerializedName("opensearch:totalResults")
    val totalResults: String,

    @SerializedName("opensearch:startIndex")
    val startIndex: String,

    @SerializedName("opensearch:itemsPerPage")
    val itemsPerPage: String,

    val albumMatches: AlbumMatches
)

data class AlbumMatches(val album: List<Album>)

data class Album(val name: String, val artist: String, val url: String, val image: List<Image>)

data class Image(
    @SerializedName("#text")
    val url: String,

    val size: String
)