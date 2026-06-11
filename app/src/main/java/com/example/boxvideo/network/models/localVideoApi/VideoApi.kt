package com.example.boxvideo.network.models.localVideoApi

interface VideoApi {
    suspend fun getVideos(): List<VideoDto>
}