package com.example.boxvideo.repository.authorizationRepo

import com.example.boxvideo.AppState
import kotlinx.coroutines.flow.Flow

interface AuthorizationRepository {
    suspend fun checkAuth(): Boolean

    suspend fun login(login: String, password: String): Boolean
    suspend fun register(login: String, email: String, password: String): Boolean
    suspend fun logOut()

    suspend fun setAuthState(state: AppState)

    fun observeAuthState(): Flow<AppState>
}