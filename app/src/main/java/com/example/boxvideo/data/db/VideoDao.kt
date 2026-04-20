package com.example.boxvideo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(videos: List<VideoEntity>)

    @Query("SELECT id, title, thumbnailUrl FROM videos")
    fun getAllVideos(): Flow<List<VideoPreview>>

    @Query("SELECT * FROM videos WHERE id = :id")
    suspend fun getVideoById(id: Int): VideoEntity?

    @Query("DELETE FROM videos WHERE id = :id")
    suspend fun deleteVideoById(id: Int)
    @Query("DELETE FROM videos WHERE id NOT IN (:ids)")
    suspend fun deleteNotIn(ids: List<Int>)

    @Query("DELETE FROM videos")
    suspend fun deleteAll()
}