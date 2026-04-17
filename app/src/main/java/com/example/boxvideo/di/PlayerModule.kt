package com.example.boxvideo.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.boxvideo.repository.VideoServiceAndVideoPlayerUiBinder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PlayerModule {
    @Provides
    @Singleton
    fun provideExoPlayer(
        playerBinder: VideoServiceAndVideoPlayerUiBinder
    ): ExoPlayer? {
        return playerBinder.getPlayer()
    }
}