package com.example.boxvideo.data.remoteDataSource.video.mapper


import com.example.boxvideo.data.remoteDataSource.video.model.video.VideoSourceDto
import com.example.boxvideo.domain.model.VideoSource
import com.example.boxvideo.domain.mapper.Mapper
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