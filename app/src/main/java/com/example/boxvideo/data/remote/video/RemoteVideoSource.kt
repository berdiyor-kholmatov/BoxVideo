package com.example.boxvideo.data.remote.video

import com.example.boxvideo.data.remote.video.model.video.VideoDto


interface RemoteVideoSource {
    suspend fun getVideos(): List<VideoDto>

    suspend fun getVideoById(id: Int): VideoDto?

    suspend fun searchVideo(query: String): List<VideoDto>
}