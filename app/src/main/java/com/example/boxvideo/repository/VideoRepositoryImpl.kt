package com.example.boxvideo.repository

import androidx.room.withTransaction
import com.example.boxvideo.data.db.VideoDao
import com.example.boxvideo.data.db.VideoDatabase
import com.example.boxvideo.data.db.mapper.VideoWithSourcesMapper
import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.data.remote.mapper.DtoMapper
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoApi: VideoApi,
    private val videoDao: VideoDao,
    private val videoDtoMapper: DtoMapper,
    private val videoWithSourcesMapper: VideoWithSourcesMapper,
    private val database: VideoDatabase
) : VideoRepository {
    override suspend fun getVideos() {
        val listOfVideosEntity = videoApi.getVideos().map{
            videoWithSourcesMapper.domainToModel(videoDtoMapper.modelToDomain(it))
        }
        database.withTransaction {
            if (listOfVideosEntity.isNotEmpty()) {
                videoDao.deleteNotIn(listOfVideosEntity.map { it.video.id })
            } else {
                videoDao.deleteAll()
            }
            videoDao.insert(listOfVideosEntity.map { it.video })
            videoDao.insert(listOfVideosEntity.flatMap { it.sources })
        }
    }

    override fun observeVideoPreviews(): Flow<List<VideoPreview>> {
        return videoDao.observeAllVideos().map { list ->
            list.map {
                VideoPreview(
                    id = it.id,
                    title = it.title,
                    thumbnailUrl = it.thumbnailUrl
                )
            }
        }
    }

    override suspend fun getVideoFileById(id: Int): Flow<VideoFile?> {
        return videoDao.getVideoById(id).map { video ->
            video?.let{
                videoWithSourcesMapper.modelToDomain(it)
            }
        }
    }

    override suspend fun insert(videoFile: VideoFile) {

        val videoWithSources = videoWithSourcesMapper.domainToModel(videoFile)

        database.withTransaction {
            videoDao.insert(listOf(videoWithSources.video))
            videoDao.insert(videoWithSources.sources)
        }
    }

    override suspend fun delete(id: Int) {
        videoDao.deleteVideoById(id)
    }

}