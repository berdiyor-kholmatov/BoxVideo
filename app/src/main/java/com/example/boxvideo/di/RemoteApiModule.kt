package com.example.boxvideo.di

import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.data.remote.VideoApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteApiModule {
    @Provides
    fun provideVideoApi(): VideoApi {
        return VideoApiImpl()
    }
}