package com.example.boxvideo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VideoEntity::class], version = 1, exportSchema = false)
abstract class VideoDatabase: RoomDatabase() {
    abstract fun getVideoDao(): VideoDao
}