package com.example.boxvideo.data.local.mockvideo


interface VideoSource {
    suspend fun getVideos(): List<com.example.boxvideo.data.local.mockvideo.model.MockVideoDto>
}