package com.hardiktrivedi.letthemusicplay.detail

import androidx.lifecycle.ViewModel
import com.hardiktrivedi.letthemusicplay.data.model.AlbumObject
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * ViewModel connects to repository and fetches the album detail
 * @param albumRepository : An injected instance of repository which gets the data from cloud.
 */
@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {
    fun getAlbumDetail(album: String, artist: String): Flow<AlbumObject> {
        return albumRepository.fetchAlbumDetail(album, artist).map {
            it.album
        }
    }
}