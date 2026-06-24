package com.example.boxvideo.data.datasource.remoteVideoSource.video.model.video

import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<VideoSourceDto>
)
