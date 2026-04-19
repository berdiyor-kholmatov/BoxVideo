package com.example.boxvideo.repository

import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    fun getVideoPreviews(): Flow<List<VideoPreview>>
    suspend fun getVideoFileById(id: Int): VideoFile?
    suspend fun insert(videoFile: VideoFile)
    suspend fun delete(videoFile: VideoFile)
}