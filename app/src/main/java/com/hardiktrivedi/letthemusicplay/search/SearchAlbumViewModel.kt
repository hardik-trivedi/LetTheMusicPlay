package com.hardiktrivedi.letthemusicplay.search

import androidx.lifecycle.ViewModel
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchAlbumViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {
    fun performSearch(newSearch: String?): Flow<List<Album>> {
        return if (newSearch != null && newSearch.isNotBlank()) {
            albumRepository.searchAlbum(newSearch)
        } else {
            //TODO Handle gracefully
            throw IllegalArgumentException("Search query cannot be empty or null")
        }
    }
}