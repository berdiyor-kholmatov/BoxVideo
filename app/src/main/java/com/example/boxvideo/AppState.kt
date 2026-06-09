package com.example.boxvideo

sealed interface AppState {

    data object Loading : AppState

    data object Authorized : AppState

    data object Unauthorized : AppState
}