package com.example.boxvideo.di

import com.example.boxvideo.repository.VideoRepository
import com.example.boxvideo.repository.VideoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideVideoRepository(impl: VideoRepositoryImpl): VideoRepository {
        return impl
    }
}
