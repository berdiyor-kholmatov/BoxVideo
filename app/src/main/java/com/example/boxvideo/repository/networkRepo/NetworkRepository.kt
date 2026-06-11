package com.example.boxvideo.repository.networkRepo

interface NetworkRepository {
    suspend fun checkAuth(): Boolean
    suspend fun login(login: String, password: String): Boolean
//    suspend fun register()
    suspend fun logOut()
}