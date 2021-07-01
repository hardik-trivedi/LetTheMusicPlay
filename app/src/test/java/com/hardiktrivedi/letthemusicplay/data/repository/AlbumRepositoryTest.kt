package com.hardiktrivedi.letthemusicplay.data.repository

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import com.hardiktrivedi.letthemusicplay.data.model.Album
import com.hardiktrivedi.letthemusicplay.data.model.AlbumMatches
import com.hardiktrivedi.letthemusicplay.data.model.AlbumSearchResponse
import com.hardiktrivedi.letthemusicplay.data.model.Results
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class AlbumRepositoryTest {
    @MockK
    private lateinit var apiService: AlbumApiService

    @MockK
    private lateinit var albumSearchResponse: AlbumSearchResponse

    @MockK
    private lateinit var results: Results

    @MockK
    private lateinit var albumMatches: AlbumMatches

    @MockK
    private lateinit var album: Album

    private lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        albumRepository = AlbumRepository(apiService)
    }

    @Test
    fun `search album returns result`() {
        val albums = listOf(album, album)
        every { albumSearchResponse.results } returns results
        every { results.albumMatches } returns albumMatches
        every { albumMatches.album } returns albums
        coEvery { apiService.searchByAlbumName("Believe") } returns albumSearchResponse

        runBlockingTest {
            val result = albumRepository.searchAlbum("Believe").first()
            assertEquals(albums, result)
        }
    }
}