package com.example.boxvideo.data.localDataSource

import com.example.boxvideo.data.localDataSource.model.MockVideoDto


interface VideoSource {
    suspend fun getVideos(): List<MockVideoDto>
}