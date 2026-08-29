package com.example.boxvideo.data.local.mockvideo.mapper


import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class MockQualityDtoMapper @Inject constructor(): Mapper<com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto, VideoQuality> {
    override fun domainToModel(domain: VideoQuality): com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto {
        return when (domain) {
            VideoQuality.P480 -> _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P480
            VideoQuality.P720 -> _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P720
            VideoQuality.P1080 -> _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P1080
        }
    }

    override fun modelToDomain(model: com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto): VideoQuality {
        return when (model) {
            _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P480 -> VideoQuality.P480
            _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P720 -> VideoQuality.P720
            _root_ide_package_.com.example.boxvideo.data.local.mockvideo.model.MockVideoQualityDto.P1080 -> VideoQuality.P1080
        }
    }
}