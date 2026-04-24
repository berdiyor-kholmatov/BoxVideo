package com.example.boxvideo.repository

import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TempVideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
) : VideoRepository  {
    override suspend fun getVideos() {
        TODO("Not yet implemented")
    }

    override fun observeVideoPreviews(): Flow<List<VideoPreview>> {
        TODO("Not yet implemented")
    }

    override fun getVideoPreviews(): Flow<List<VideoPreview>> {
        TODO("Not yet implemented")
    }

    override suspend fun getVideoFileById(id: Int): VideoFile? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(videoFile: VideoFile) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}