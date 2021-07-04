package com.hardiktrivedi.letthemusicplay.api

import com.hardiktrivedi.letthemusicplay.BuildConfig
import com.hardiktrivedi.letthemusicplay.data.model.AlbumDetailResponse
import com.hardiktrivedi.letthemusicplay.data.model.AlbumSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApiService {
    @GET(".")
    suspend fun searchByAlbumName(
        @Query("album") album: String,
        @Query("method") method: String = "album.search",
        /* Please make sure you have put your last.fm api key in local.properties file. Check README.md for more info*/
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json"
    ): AlbumSearchResponse

    @GET(".")
    suspend fun getAlbumDetail(
        @Query("artist") artist: String,
        @Query("album") album: String,
        @Query("method") method: String = "album.getinfo",
        /* Please make sure you have put your last.fm api key in local.properties file. Check README.md for more info*/
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format: String = "json"
    ): AlbumDetailResponse
}