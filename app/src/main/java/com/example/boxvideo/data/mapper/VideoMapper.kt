package com.example.boxvideo.data.mapper

import com.example.boxvideo.data.db.VideoEntity
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.util.Mapper

class VideoMapper: Mapper<VideoEntity, VideoFile> {
    override fun domainToModel(domain: VideoFile): VideoEntity {
        return VideoEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            thumbnailUrl = domain.thumbnailUrl,
            sources = domain.sources
        )
    }

    override fun modelToDomain(model: VideoEntity): VideoFile {
        return VideoFile(
            id = model.id,
            title = model.title,
            description = model.description,
            thumbnailUrl = model.thumbnailUrl,
            sources = model.sources
        )
    }
}