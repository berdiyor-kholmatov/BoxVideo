package com.example.boxvideo.di

import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepository
import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepositoryImpl
import com.example.boxvideo.repository.videoRepo.VideoRepository
import com.example.boxvideo.repository.videoRepo.VideoRepositoryImpl
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

    @Provides
    fun provideNetworkRepository(impl: AuthorizationRepositoryImpl): AuthorizationRepository {
        return impl
    }
}
