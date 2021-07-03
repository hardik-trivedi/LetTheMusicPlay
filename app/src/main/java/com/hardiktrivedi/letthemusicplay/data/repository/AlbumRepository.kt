package com.hardiktrivedi.letthemusicplay.data.repository

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.model.AlbumDetailResponse
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlbumRepository @Inject constructor(private val apiService: AlbumApiService) {
    fun searchAlbum(searchValue: String) = flow<List<Album>> {
        emit(apiService.searchByAlbumName(album = searchValue).results.albumMatches.album)
    }

    fun fetchAlbumDetail(album: String, artist: String) = flow<AlbumDetailResponse> {
        emit(apiService.getAlbumDetail(album, artist))
    }
}