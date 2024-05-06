package com.anisuki.animewallpapers.di

import com.anisuki.animewallpapers.common.Constants.BASE_URL
import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.repository.PopularRepo
import com.anisuki.animewallpapers.repository.WallpapersRepo
import com.anisuki.animewallpapers.repository.impl.PopularRepoImpl
import com.anisuki.animewallpapers.repository.impl.WallpapersRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(builder:Retrofit.Builder): ApiService {
        return builder
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun provideWallpapersRepository(api: ApiService): WallpapersRepo = WallpapersRepoImpl(api)

    @Provides
    fun providePopularRepository(api: ApiService): PopularRepo = PopularRepoImpl(api)
}