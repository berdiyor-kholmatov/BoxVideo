package com.example.boxvideo.network.models.mapper

import com.example.boxvideo.network.models.localVideoApi.VideoDto
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class DtoMapper @Inject constructor(
    private val sourceDtoMapper: SourceDtoMapper
): Mapper<VideoDto, VideoFile> {
    override fun domainToModel(domain: VideoFile): VideoDto {
        return VideoDto(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            thumbnailUrl = domain.thumbnailUrl,
            sources = domain.sources.map { source ->
                sourceDtoMapper.domainToModel(source)
            }
        )
    }

    override fun modelToDomain(model: VideoDto): VideoFile {
        return VideoFile(
            id = model.id,
            title = model.title,
            description = model.description,
            thumbnailUrl = model.thumbnailUrl,
            sources = model.sources.map { source ->
                sourceDtoMapper.modelToDomain(source)
            }
        )
    }
}