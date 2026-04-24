package com.example.boxvideo.data.remote.mapper

import com.example.boxvideo.data.remote.VideoSourceDto
import com.example.boxvideo.domain.model.VideoSource
import com.example.boxvideo.domain.util.Mapper
import javax.inject.Inject

class SourceDtoMapper @Inject constructor(
    private val qualityDtoMapper: QualityDtoMapper
): Mapper<VideoSourceDto, VideoSource> {

    override fun domainToModel(domain: VideoSource): VideoSourceDto {
        return VideoSourceDto(
            quality = qualityDtoMapper.domainToModel(domain.quality),
            url = domain.url
        )
    }

    override fun modelToDomain(model: VideoSourceDto): VideoSource {
        return VideoSource(
            quality = qualityDtoMapper.modelToDomain(model.quality),
            url = model.url
        )
    }
}