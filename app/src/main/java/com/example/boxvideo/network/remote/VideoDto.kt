package com.example.boxvideo.network.remote

data class VideoDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<VideoSourceDto>
)
