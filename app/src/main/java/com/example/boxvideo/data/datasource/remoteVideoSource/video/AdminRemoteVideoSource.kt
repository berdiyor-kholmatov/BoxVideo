package com.example.boxvideo.data.datasource.remoteVideoSource.video

import com.example.boxvideo.data.datasource.remoteVideoSource.video.model.VideoDto

interface AdminRemoteVideoSource: RemoteVideoSource {

    suspend fun addVideo(video: VideoDto): Int

    suspend fun updateVideo(id: Int, video: VideoDto)

    suspend fun deleteVideo(id: Int)
}