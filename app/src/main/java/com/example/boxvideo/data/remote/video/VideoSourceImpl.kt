package com.example.boxvideo.data.remote.video

import android.util.Log
import com.example.boxvideo.BuildConfig
import com.example.boxvideo.data.local.datastore.token.TokenStorage
import com.example.boxvideo.data.remote.NetworkClient
import com.example.boxvideo.data.remote.video.model.admin.AddVideoRespond
import com.example.boxvideo.data.remote.video.model.video.VideoDto
import io.ktor.util.reflect.typeInfo
import javax.inject.Inject

class VideoSourceImpl @Inject constructor(
    private val networkClient: NetworkClient,
    private val tokenStorage: TokenStorage
) : AdminRemoteVideoSource {
    override suspend fun getVideos(): List<VideoDto> {

        val token = tokenStorage.getToken() ?: return emptyList()

        try {
            val videos = networkClient.get<List<VideoDto>>(
                url = "${BuildConfig.BASE_URL}/videos",
                headers = mapOf("Authorization" to "Bearer $token"),
                typeInfo = typeInfo<List<VideoDto>>()
            )
            return videos
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
            return emptyList()
        }
    }

    override suspend fun getVideoById(id: Int): VideoDto? {
        val token = tokenStorage.getToken() ?: return null

        try {
            val video = networkClient.get(
                url = "${BuildConfig.BASE_URL}/videos/$id",
                headers = mapOf("Authorization" to "Bearer $token"),
                responseType = VideoDto::class
            )
            return video
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
            return null
        }
    }

    override suspend fun searchVideo(query: String): List<VideoDto> {
        val token = tokenStorage.getToken() ?: return emptyList()

        try {
            val videos = networkClient.get(
                url = "${BuildConfig.BASE_URL}/videos/search",
                headers = mapOf("Authorization" to "Bearer $token"),
                responseType = Array<VideoDto>::class // because of KClass we can't send List<VideoDto>, the type of list will be erased during runtime operation, so we can do it with Array which can do so
            )
            return videos.toList()
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
            return emptyList()
        }
    }

    override suspend fun addVideo(video: VideoDto): Int? {

        val token = tokenStorage.getToken() ?: return null

        try {
            val respond = networkClient.post(
                url = "${BuildConfig.BASE_URL}/admin/videos",
                headers = mapOf("Authorization" to "Bearer $token"),
                body = video,
                responseType = AddVideoRespond::class
            )
            return respond.id
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
            return null
        }
    }

    override suspend fun updateVideo(
        id: Int,
        video: VideoDto
    ) {
        val token = tokenStorage.getToken() ?: return

        try {
            val respond = networkClient.put(
                url = "${BuildConfig.BASE_URL}/admin/videos/$id",
                headers = mapOf("Authorization" to "Bearer $token"),
                body = video,
            )
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
        }
    }

    override suspend fun deleteVideo(id: Int) {
        val token = tokenStorage.getToken() ?: return

        try {
            val respond = networkClient.delete(
                url = "${BuildConfig.BASE_URL}/admin/videos/$id",
                headers = mapOf("Authorization" to "Bearer $token")
            )
        } catch (e: Exception) {
            Log.e("net", "Error: ${e.message}", e)
        }
    }
}