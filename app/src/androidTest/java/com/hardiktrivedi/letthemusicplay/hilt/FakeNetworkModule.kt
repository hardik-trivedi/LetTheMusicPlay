package com.hardiktrivedi.letthemusicplay.hilt

import com.hardiktrivedi.letthemusicplay.api.AlbumApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FakeNetworkModule {

    @Provides
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideAlbumApiService(baseUrl: String, client: OkHttpClient): AlbumApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "http://127.0.0.1:8080/"
}