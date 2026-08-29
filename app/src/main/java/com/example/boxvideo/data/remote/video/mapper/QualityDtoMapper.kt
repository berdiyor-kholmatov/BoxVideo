package com.example.boxvideo.data.remote.video.mapper


import com.example.boxvideo.data.remote.video.model.video.VideoQualityDto
import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class QualityDtoMapper @Inject constructor(): Mapper<VideoQualityDto, VideoQuality> {
    override fun domainToModel(domain: VideoQuality): VideoQualityDto {
        return when (domain) {
            VideoQuality.P480 -> VideoQualityDto.P480
            VideoQuality.P720 -> VideoQualityDto.P720
            VideoQuality.P1080 -> VideoQualityDto.P1080
        }
    }

    override fun modelToDomain(model: VideoQualityDto): VideoQuality {
        return when (model) {
            VideoQualityDto.P480 -> VideoQuality.P480
            VideoQualityDto.P720 -> VideoQuality.P720
            VideoQualityDto.P1080 -> VideoQuality.P1080
        }
    }
}