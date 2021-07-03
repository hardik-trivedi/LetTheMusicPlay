package com.hardiktrivedi.letthemusicplay.detail

import androidx.lifecycle.ViewModel
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    ViewModel() {

}