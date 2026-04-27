package com.example.boxvideo.ui.movieList

import com.example.boxvideo.domain.model.VideoPreview

data class MovieState (
    val videoPreviews: List<VideoPreview> = emptyList(),
)