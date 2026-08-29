package com.example.boxvideo.data.local.datastore.authstate

import com.example.boxvideo.AppState
import kotlinx.coroutines.flow.Flow

interface AuthorizationStorage {

    suspend fun setAuthState(state: AppState)

    fun observeAuthState(): Flow<AppState>

}