package com.example.boxvideo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
@TypeConverters(VideoSourceConverter::class)
abstract class VideoDatabase: RoomDatabase() {
    abstract fun getVideoDao(): VideoDao
}