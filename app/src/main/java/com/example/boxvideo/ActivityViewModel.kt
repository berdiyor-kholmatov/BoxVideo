package com.example.boxvideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.repository.networkRepo.NetworkRepository
import com.example.boxvideo.repository.videoRepo.VideoRepository
import com.example.boxvideo.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val sessionManager: SessionManager
): ViewModel() {

    private val _appState = MutableStateFlow<AppState>(AppState.Loading)
    val appState = _appState.asStateFlow()

    init {
        viewModelScope.launch {
            sessionManager.appState.collect {
                _appState.value = it
            }
        }
    }


    init {
        viewModelScope.launch {
                if(networkRepository.checkAuth()) sessionManager.authorize() else sessionManager.unauthorize()
        }
    }


}