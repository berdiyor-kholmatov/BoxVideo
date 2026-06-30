package com.example.boxvideo.data.remoteDataSource.video.model.video

import kotlinx.serialization.Serializable

@Serializable
data class VideoSourceDto(
    val quality: VideoQualityDto,
    val url: String,
)