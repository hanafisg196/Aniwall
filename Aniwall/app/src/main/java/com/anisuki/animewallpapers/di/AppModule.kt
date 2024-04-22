package com.anisuki.animewallpapers.di

import android.app.Application
import com.anisuki.animewallpapers.data.manager.LocalUserManagerImpl
import com.anisuki.animewallpapers.data.remote.Api
import com.anisuki.animewallpapers.data.repository.RandomRepositoryImpl
import com.anisuki.animewallpapers.domain.manager.LocalUserManager
import com.anisuki.animewallpapers.domain.repository.RandomRepository
import com.anisuki.animewallpapers.domain.usecases.AppEntryUseCases
import com.anisuki.animewallpapers.domain.usecases.ReadAppEntry
import com.anisuki.animewallpapers.domain.usecases.SaveAppEntry
import com.anisuki.animewallpapers.common.Constants
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
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )


    @Provides
    @Singleton
    fun provideApi(): Api{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRandomRepository(api: Api): RandomRepository{
        return RandomRepositoryImpl(api)
    }
}