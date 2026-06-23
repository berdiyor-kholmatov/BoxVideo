package com.example.boxvideo.data.datasource.localVideoSource.mapper

import com.example.boxvideo.data.datasource.localVideoSource.model.MockVideoSourceDto
import com.example.boxvideo.domain.model.VideoSource
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class MockSourceDtoMapper @Inject constructor(
    private val mockQualityDtoMapper: MockQualityDtoMapper
): Mapper<MockVideoSourceDto, VideoSource> {

    override fun domainToModel(domain: VideoSource): MockVideoSourceDto {
        return MockVideoSourceDto(
            quality = mockQualityDtoMapper.domainToModel(domain.quality),
            url = domain.url
        )
    }

    override fun modelToDomain(model: MockVideoSourceDto): VideoSource {
        return VideoSource(
            quality = mockQualityDtoMapper.modelToDomain(model.quality),
            url = model.url
        )
    }
}