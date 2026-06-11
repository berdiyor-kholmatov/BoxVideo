package com.example.boxvideo.network.models.localVideoApi

data class VideoDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<VideoSourceDto>
)
