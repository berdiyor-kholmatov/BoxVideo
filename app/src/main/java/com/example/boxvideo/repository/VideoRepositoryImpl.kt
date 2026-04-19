package com.example.boxvideo.repository

import com.example.boxvideo.data.db.VideoDao
import com.example.boxvideo.data.mapper.VideoMapper
import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val videoDao: VideoDao,
    private val mapper: VideoMapper
) : VideoRepository {
    override fun getVideoPreviews(): Flow<List<VideoPreview>> {
        TODO("Not yet implemented")
    }

    override suspend fun getVideoFileById(id: Int): VideoFile? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(videoFile: VideoFile) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(videoFile: VideoFile) {
        TODO("Not yet implemented")
    }

}