package com.example.boxvideo

import androidx.lifecycle.ViewModel
import com.example.boxvideo.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: VideoRepository
): ViewModel() {

    private val _appState = MutableStateFlow<AppState>(AppState.Loading)
    val appState = _appState.asStateFlow()

    init {
        checkAuth()
    }

    private fun checkAuth(){

    }


}