package com.example.boxvideo.data.remote

interface VideoApi {
    suspend fun getVideos(): List<VideoDto>
}