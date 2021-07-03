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

    @SerializedName("albummatches")
    val albumMatches: AlbumMatches
)

data class AlbumMatches(val album: List<Album>)

data class Album(val name: String, val artist: String, val url: String, val image: List<Image>)

data class Image(
    @SerializedName("#text")
    val url: String,

    val size: String
)

data class AlbumDetailResponse(val album: AlbumObject)

data class AlbumObject(
    val listeners: String,
    @SerializedName("playcount") val playCount: String,
    val wiki: Wiki,
    val tracks: Tracks?,
    val image: List<Image>,
    val name: String,
    val artist: String
)

data class Wiki(val published: String, val content: String, val summary: String)

data class Track(val artist: Artist, val duration: Int, val name: String)

data class Tracks(val track: List<Track>)

data class Artist(val url: String, val name: String, val mbid: String)