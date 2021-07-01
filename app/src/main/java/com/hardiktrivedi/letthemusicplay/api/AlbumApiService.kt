package com.hardiktrivedi.letthemusicplay.api

import com.hardiktrivedi.letthemusicplay.data.model.AlbumSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

// TODO Put api key on proper place
interface AlbumApiService {
    @GET(".")
    suspend fun searchByAlbumName(
        @Query("album") album: String,
        @Query("method") method: String = "album.search",
        @Query("api_key") apiKey: String = "cc673eb8b6d5e4d1875d357fefccaef1",
        @Query("format") format: String = "json"
    ): AlbumSearchResponse
}