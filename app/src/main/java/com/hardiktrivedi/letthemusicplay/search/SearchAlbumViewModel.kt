package com.hardiktrivedi.letthemusicplay.search

import androidx.lifecycle.ViewModel
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchAlbumViewModel @Inject constructor (val albumRepository: AlbumRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}