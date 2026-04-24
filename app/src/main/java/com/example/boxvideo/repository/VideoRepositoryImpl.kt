package com.example.boxvideo.repository

import androidx.room.withTransaction
import com.example.boxvideo.data.db.VideoDao
import com.example.boxvideo.data.db.VideoDatabase
import com.example.boxvideo.data.mapper.VideoMapper
import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.data.remote.mapper.DtoMapper
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val videoDao: VideoDao,
    private val videoDtoMapper: DtoMapper,
    private val database: VideoDatabase
) : VideoRepository {
    override suspend fun getVideos() {
        val listOfVideosEntity = videoApi.getVideos().map{
            videoDtoMapper.modelToDomain(it)
        }
        database.withTransaction {
            if (listOfVideosEntity.isNotEmpty()) {
                videoDao.deleteNotIn(listOfVideosEntity.map { it.id })
            } else {
                videoDao.deleteAll()
            }
            videoDao.insert(listOfVideosEntity)
        }
    }

    override fun observeVideoPreviews(): Flow<List<VideoPreview>> {
        return videoDao.observeAllVideos()
    }

    override suspend fun getVideoFileById(id: Int): VideoFile? {
        return videoDao.getVideoById(id)?.let { mapper.modelToDomain(it) }
    }

    override suspend fun insert(videoFile: VideoFile) {
        videoDao.insert(listOf(mapper.domainToModel(videoFile)))
    }

    override suspend fun delete(id: Int) {
        videoDao.deleteVideoById(id)
    }

}