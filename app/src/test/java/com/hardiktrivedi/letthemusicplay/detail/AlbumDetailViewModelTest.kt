package com.hardiktrivedi.letthemusicplay.detail

import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.model.AlbumDetailResponse
import com.hardiktrivedi.letthemusicplay.data.model.AlbumObject
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import com.hardiktrivedi.letthemusicplay.search.SearchAlbumViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class AlbumDetailViewModelTest{
    @MockK
    private lateinit var albumRepository: AlbumRepository

    @MockK
    private lateinit var albumDetailResponse: AlbumDetailResponse

    @MockK
    private lateinit var album: AlbumObject

    private lateinit var albumDetailViewModel: AlbumDetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumDetailViewModel = AlbumDetailViewModel(albumRepository)
    }

    @Test
    fun `album detail is fetched`() {
        every { albumRepository.fetchAlbumDetail("Blonde", "Frank Ocean") } returns flowOf(albumDetailResponse)
        every { albumDetailResponse.album } returns album

        runBlockingTest {
            val result = albumDetailViewModel.getAlbumDetail("Blonde", "Frank Ocean").first()
            assertEquals(album, result)
        }
    }
}