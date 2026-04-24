package com.example.boxvideo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [VideoEntity::class, VideoSourceEntity::class], version = 1, exportSchema = false)
abstract class VideoDatabase: RoomDatabase() {
    abstract fun videoDao(): VideoDao
}