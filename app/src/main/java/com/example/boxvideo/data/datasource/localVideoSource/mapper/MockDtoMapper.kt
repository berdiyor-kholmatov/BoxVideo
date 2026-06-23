package com.example.boxvideo.data.datasource.localVideoSource.mapper

import com.example.boxvideo.data.datasource.localVideoSource.model.MockVideoDto
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class MockDtoMapper @Inject constructor(
    private val mockSourceDtoMapper: MockSourceDtoMapper
): Mapper<MockVideoDto, VideoFile> {
    override fun domainToModel(domain: VideoFile): MockVideoDto {
        return MockVideoDto(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            thumbnailUrl = domain.thumbnailUrl,
            sources = domain.sources.map { source ->
                mockSourceDtoMapper.domainToModel(source)
            }
        )
    }

    override fun modelToDomain(model: MockVideoDto): VideoFile {
        return VideoFile(
            id = model.id,
            title = model.title,
            description = model.description,
            thumbnailUrl = model.thumbnailUrl,
            sources = model.sources.map { source ->
                mockSourceDtoMapper.modelToDomain(source)
            }
        )
    }
}