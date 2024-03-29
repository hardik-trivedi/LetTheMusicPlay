package com.hardiktrivedi.letthemusicplay.search

import androidx.lifecycle.ViewModel
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * ViewModel connects to repository and fetches the album list based on search query
 * @param albumRepository : An injected instance of repository which gets the data from cloud.
 */
@HiltViewModel
class SearchAlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {
    fun performSearch(newSearch: String?) = flow {
        if (newSearch != null && newSearch.isNotBlank()) {
            albumRepository.searchAlbum(newSearch).collect {
                emit(it)
            }
        } else {
            throw IllegalArgumentException()
        }
    }
}