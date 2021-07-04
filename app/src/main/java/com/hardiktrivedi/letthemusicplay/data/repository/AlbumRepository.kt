package com.hardiktrivedi.letthemusicplay.data.repository

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.model.AlbumDetailResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository which gets the data from cloud.
 *
 * @param apiService : An injected instance of [AlbumApiService] which is interface. Retrofit will create it's instance
 */
class AlbumRepository @Inject constructor(private val apiService: AlbumApiService) {
    /**
     * Searches album
     * @param searchValue search key, data will be fetched based on this value
     *
     * @return Flow<List<Album>> returns parsed JSON response
     */
    fun searchAlbum(searchValue: String) = flow<List<Album>> {
        emit(apiService.searchByAlbumName(album = searchValue).results.albumMatches.album)
    }

    /**
     * Fetches album detail
     * @param album name of album
     * @param artist name of artist
     *
     * @return Flow<AlbumDetailResponse> returns parsed JSON response
     */
    fun fetchAlbumDetail(album: String, artist: String) = flow<AlbumDetailResponse> {
        emit(apiService.getAlbumDetail(album, artist))
    }
}