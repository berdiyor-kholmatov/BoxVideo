package com.example.boxvideo.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.repository.networkRepo.NetworkRepository
import com.example.boxvideo.repository.videoRepo.VideoRepository
import com.example.boxvideo.session.SessionManager
import com.example.boxvideo.ui.player.PlayerEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val repository: VideoRepository,
    private val sessionManager: SessionManager,
    private val networkRepository: NetworkRepository
): ViewModel() {

    val state: StateFlow<MovieState> =
        repository.observeVideoPreviews()
            .map { previews ->
                MovieState(videoPreviews = previews)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieState()
            )


    init {
        viewModelScope.launch {
            repository.getVideos()
        }
    }

        fun onEvent(event: MovieEvents){
            when(event) {
                MovieEvents.LogOut -> {
                    viewModelScope.launch {
                        networkRepository.logOut()
                    }
                    sessionManager.unauthorize()
                }
            }
        }

}