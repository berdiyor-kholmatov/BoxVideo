package com.example.boxvideo.data.localDataSource.mapper


import com.example.boxvideo.data.localDataSource.model.MockVideoQualityDto
import com.example.boxvideo.domain.model.VideoQuality
import com.example.boxvideo.domain.mapper.Mapper
import javax.inject.Inject

class MockQualityDtoMapper @Inject constructor(): Mapper<MockVideoQualityDto, VideoQuality> {
    override fun domainToModel(domain: VideoQuality): MockVideoQualityDto {
        return when (domain) {
            VideoQuality.P480 -> MockVideoQualityDto.P480
            VideoQuality.P720 -> MockVideoQualityDto.P720
            VideoQuality.P1080 -> MockVideoQualityDto.P1080
        }
    }

    override fun modelToDomain(model: MockVideoQualityDto): VideoQuality {
        return when (model) {
            MockVideoQualityDto.P480 -> VideoQuality.P480
            MockVideoQualityDto.P720 -> VideoQuality.P720
            MockVideoQualityDto.P1080 -> VideoQuality.P1080
        }
    }
}