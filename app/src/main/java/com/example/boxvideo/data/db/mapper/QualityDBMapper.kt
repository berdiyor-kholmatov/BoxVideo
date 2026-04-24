package com.example.boxvideo.data.db.mapper

import com.example.boxvideo.data.db.VideoQualityDB
import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class QualityDBMapper @Inject constructor(): Mapper<VideoQualityDB, VideoQuality> {
    override fun domainToModel(domain: VideoQuality): VideoQualityDB {
        return when (domain) {
            VideoQuality.P480 -> VideoQualityDB.P480
            VideoQuality.P720 -> VideoQualityDB.P720
            VideoQuality.P1080 -> VideoQualityDB.P1080
        }
    }

    override fun modelToDomain(model: VideoQualityDB): VideoQuality {
        return when (model) {
            VideoQualityDB.P480 -> VideoQuality.P480
            VideoQualityDB.P720 -> VideoQuality.P720
            VideoQualityDB.P1080 -> VideoQuality.P1080
        }
    }
}