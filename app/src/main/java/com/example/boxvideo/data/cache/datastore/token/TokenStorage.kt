package com.example.boxvideo.data.cache.datastore.token

interface TokenStorage {
    suspend fun saveToken(
        token: String
    )

    suspend fun getToken(): String?

    suspend fun clearToken()
}