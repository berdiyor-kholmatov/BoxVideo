package com.example.boxvideo.session

import com.example.boxvideo.AppState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val _appState = MutableStateFlow<AppState>(AppState.Loading)
    val appState = _appState.asStateFlow()

    fun authorize(){
        _appState.value = AppState.Authorized
    }

    fun unauthorize(){
        _appState.value = AppState.Unauthorized
    }
}