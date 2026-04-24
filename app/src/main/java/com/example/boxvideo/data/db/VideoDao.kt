package com.example.boxvideo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videos: List<VideoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videoSource: List<VideoSourceEntity>)

    @Query("SELECT id, title, thumbnailUrl FROM videos")
    fun observeAllVideos(): Flow<List<VideoPreviewDB>>

    @Transaction
    @Query("SELECT * FROM videos WHERE id = :id")
    suspend fun getVideoById(id: Int): Flow<VideoWithSources?>

    @Query("DELETE FROM videos WHERE id NOT IN (:ids)")
    suspend fun deleteNotIn(ids: List<Int>)

    @Query("DELETE FROM videos WHERE id = :id")
    suspend fun deleteVideoById(id: Int)

    @Query("DELETE FROM videos")
    suspend fun deleteAll()
}