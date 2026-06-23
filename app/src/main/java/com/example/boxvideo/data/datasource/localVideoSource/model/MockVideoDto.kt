package com.example.boxvideo.data.datasource.localVideoSource.model

import kotlinx.serialization.Serializable

@Serializable
data class MockVideoDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<MockVideoSourceDto>
)
