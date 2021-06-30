package com.hardiktrivedi.letthemusicplay.hilt

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import com.hardiktrivedi.letthemusicplay.data.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideAlbumApiService(): AlbumApiService {
        return Retrofit.Builder()
            .baseUrl("ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumApiService::class.java)
    }
}