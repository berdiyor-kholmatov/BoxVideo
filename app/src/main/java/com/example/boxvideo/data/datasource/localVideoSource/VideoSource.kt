package com.example.boxvideo.data.datasource.localVideoSource

import com.example.boxvideo.data.datasource.localVideoSource.model.MockVideoDto

interface VideoSource {
    suspend fun getVideos(): List<MockVideoDto>
}