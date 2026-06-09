package com.example.boxvideo.data.datastore

interface TokenStorage {
    suspend fun saveToken(
        token: String
    )

    suspend fun getToken(): String?

    suspend fun clearToken()
}