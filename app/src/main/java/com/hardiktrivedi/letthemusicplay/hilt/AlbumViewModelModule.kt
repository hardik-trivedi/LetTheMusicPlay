package com.hardiktrivedi.letthemusicplay.hilt

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AlbumViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideAlbumRepository(albumApiService: AlbumApiService): AlbumRepository {
        return AlbumRepository(albumApiService)
    }
}