package com.example.boxvideo.data.db.mapper

import com.example.boxvideo.data.db.VideoEntity
import com.example.boxvideo.data.db.VideoSourceEntity
import com.example.boxvideo.data.db.VideoWithSources
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.model.VideoSource
import com.example.boxvideo.domain.util.Mapper
import javax.inject.Inject

class VideoWithSourcesMapper @Inject constructor (
    private val qualityDBMapper: QualityDBMapper
): Mapper<VideoWithSources, VideoFile> {
    override fun domainToModel(domain: VideoFile): VideoWithSources {
        val videoEntity = VideoEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            thumbnailUrl = domain.thumbnailUrl
        )
        val videoSources = domain.sources.map { source ->
            VideoSourceEntity(
                videoId = domain.id,
                quality = qualityDBMapper.domainToModel(source.quality),
                url = source.url
            )
        }

        return VideoWithSources(
            video = videoEntity,
            sources = videoSources
        )
    }

    override fun modelToDomain(model: VideoWithSources): VideoFile {
        return VideoFile(
            id = model.video.id,
            title = model.video.title,
            description = model.video.description,
            thumbnailUrl = model.video.thumbnailUrl,
            sources = model.sources.map { source ->
                VideoSource(
                    quality = qualityDBMapper.modelToDomain(source.quality),
                    url = source.url
                )
            }
        )
    }
}