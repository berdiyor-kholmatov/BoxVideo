package com.example.boxvideo.data.remote.video.model.video

import kotlinx.serialization.Serializable

@Serializable
data class VideoSourceDto(
    val quality: VideoQualityDto,
    val url: String,
)