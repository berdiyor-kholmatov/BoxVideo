package com.example.boxvideo.di

import android.content.Context
import androidx.room.Room
import com.example.boxvideo.data.db.VideoDao
import com.example.boxvideo.data.db.VideoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : VideoDatabase {
        return Room.databaseBuilder(context, VideoDatabase::class.java, "videos.db")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideVideoDao(database: VideoDatabase): VideoDao = database.videoDao()

}