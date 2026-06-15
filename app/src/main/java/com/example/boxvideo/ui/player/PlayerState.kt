package com.example.boxvideo.ui.player

import com.example.boxvideo.domain.model.VideoFile

data class PlayerState (
    val videoFile: VideoFile? = null,
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0,
    val videoDuration: Long = 0,
)