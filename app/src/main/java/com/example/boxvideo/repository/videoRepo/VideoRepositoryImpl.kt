package com.example.boxvideo.repository.videoRepo

import androidx.room.withTransaction
import com.example.boxvideo.data.db.VideoDao
import com.example.boxvideo.data.db.VideoDatabase
import com.example.boxvideo.data.db.mapper.VideoWithSourcesMapper
import com.example.boxvideo.data.datasource.localVideoSource.VideoSource
import com.example.boxvideo.data.datasource.localVideoSource.mapper.MockDtoMapper
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoPreview
import com.example.boxvideo.network.client.NetworkClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoSource: VideoSource,
    private val networkClient: NetworkClient,
    private val videoDao: VideoDao,
    private val videoMockDtoMapper: MockDtoMapper,
    private val videoWithSourcesMapper: VideoWithSourcesMapper,
    private val database: VideoDatabase
) : VideoRepository {


    override suspend fun getVideos() {
        val listOfVideosEntity = videoSource.getVideos().map {
            videoWithSourcesMapper.domainToModel(videoMockDtoMapper.modelToDomain(it))
        }
        database.withTransaction {
            if (listOfVideosEntity.isNotEmpty()) {
                videoDao.deleteNotIn(listOfVideosEntity.map { it.video.id })
            } else {
                videoDao.deleteAll()
            }
            videoDao.insertVideos(listOfVideosEntity.map { it.video })
            videoDao.insertSources(listOfVideosEntity.flatMap { it.sources })
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

    override fun getVideoFileById(id: Int): Flow<VideoFile?> {
        return videoDao.getVideoById(id).map { video ->
            video?.let{
                videoWithSourcesMapper.modelToDomain(it)
            }
        }
    }

    override suspend fun insert(videoFile: VideoFile) {

        val videoWithSources = videoWithSourcesMapper.domainToModel(videoFile)

        database.withTransaction {
            videoDao.insertVideos(listOf(videoWithSources.video))
            videoDao.insertSources(videoWithSources.sources)
        }
    }

    override suspend fun delete(id: Int) {
        videoDao.deleteVideoById(id)
    }

}