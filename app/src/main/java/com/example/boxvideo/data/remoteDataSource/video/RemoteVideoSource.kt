package com.example.boxvideo.data.remoteDataSource.video

import com.example.boxvideo.data.remoteDataSource.video.model.video.VideoDto


interface RemoteVideoSource {
    suspend fun getVideos(): List<VideoDto>

    suspend fun getVideoById(id: Int): VideoDto?

    suspend fun searchVideo(query: String): List<VideoDto>
}