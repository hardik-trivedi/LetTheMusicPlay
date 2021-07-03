package com.hardiktrivedi.letthemusicplay.search

import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchAlbumViewModelTest {
    @MockK
    private lateinit var albumRepository: AlbumRepository

    @MockK
    private lateinit var album: Album

    private lateinit var searchAlbumViewModel: SearchAlbumViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        searchAlbumViewModel = SearchAlbumViewModel(albumRepository)
    }

    @Test
    fun `search can be performed and result is returned`() {
        val albums = listOf(album, album, album)
        every { albumRepository.searchAlbum("Believe") } returns flowOf(albums)
        runBlockingTest {
            val result = searchAlbumViewModel.performSearch("Believe").first()
            assertEquals(albums, result)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception if search string is null`() {
        runBlockingTest {
            searchAlbumViewModel.performSearch(null).first()
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception if search string is empty`() {
        runBlockingTest {
            searchAlbumViewModel.performSearch("").first()
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `throws exception if search string is blank`() {
        val whiteSpaces = "     "
        runBlockingTest {
            searchAlbumViewModel.performSearch(whiteSpaces).first()
        }
    }
}