package com.example.boxvideo.ui.main.movieList

import com.example.boxvideo.User
import com.example.boxvideo.domain.model.VideoPreview

data class MovieState (
    val user: User = User(),
    val videoPreviews: List<VideoPreview> = emptyList(),
    val isProfileInfoExpanded: Boolean = false
)