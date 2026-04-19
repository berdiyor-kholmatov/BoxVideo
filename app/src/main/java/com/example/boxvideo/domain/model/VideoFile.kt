package com.example.boxvideo.domain.model

data class VideoFile(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val sources: List<VideoSource>
)