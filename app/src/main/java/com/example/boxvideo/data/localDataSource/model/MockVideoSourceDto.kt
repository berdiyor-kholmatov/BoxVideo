package com.example.boxvideo.data.localDataSource.model

import kotlinx.serialization.Serializable

@Serializable
data class MockVideoSourceDto(
    val quality: MockVideoQualityDto,
    val url: String,
)
