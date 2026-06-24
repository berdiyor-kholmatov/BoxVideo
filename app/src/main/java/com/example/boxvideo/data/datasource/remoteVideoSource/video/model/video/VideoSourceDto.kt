package com.example.boxvideo.data.datasource.remoteVideoSource.video.model.video

import kotlinx.serialization.Serializable

@Serializable
data class VideoSourceDto(
    val quality: VideoQualityDto,
    val url: String,
)