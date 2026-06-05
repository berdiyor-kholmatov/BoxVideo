package com.example.boxvideo.network.remote

interface VideoApi {
    suspend fun getVideos(): List<VideoDto>
}