package com.example.boxvideo.data.remote.video

import com.example.boxvideo.data.remote.video.model.video.VideoDto


interface AdminRemoteVideoSource: RemoteVideoSource {

    suspend fun addVideo(video: VideoDto): Int?

    suspend fun updateVideo(id: Int, video: VideoDto)

    suspend fun deleteVideo(id: Int)
}