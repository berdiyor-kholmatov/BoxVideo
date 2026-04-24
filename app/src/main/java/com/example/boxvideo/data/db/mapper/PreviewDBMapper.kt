package com.example.boxvideo.data.db.mapper

import com.example.boxvideo.data.db.VideoPreviewDB
import com.example.boxvideo.domain.model.VideoPreview
import com.example.boxvideo.domain.util.Mapper

class PreviewDBMapper: Mapper<VideoPreviewDB, VideoPreview> {
    override fun domainToModel(domain: VideoPreview): VideoPreviewDB {
       return VideoPreviewDB(
           id = domain.id,
           title = domain.title,
           thumbnailUrl = domain.thumbnailUrl
       )
    }

    override fun modelToDomain(model: VideoPreviewDB): VideoPreview {
        return VideoPreview(
            id = model.id,
            title = model.title,
            thumbnailUrl = model.thumbnailUrl
        )
    }
}