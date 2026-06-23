package com.example.boxvideo.ui.main.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.AppState
import com.example.boxvideo.User
import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepository
import com.example.boxvideo.repository.videoRepo.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val repository: VideoRepository,
    private val authorizationRepository: AuthorizationRepository
): ViewModel() {
    val _vm_state = MutableStateFlow(MovieState())

    val vm_state = _vm_state.asStateFlow()

    val state_main: StateFlow<MovieState> =
        repository.observeVideoPreviews()
            .map { previews ->
                MovieState(videoPreviews = previews)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = MovieState()
            )

    val state = vm_state.combine(state_main) { vm_state, state_main ->
        MovieState(
            user = vm_state.user,
            videoPreviews = state_main.videoPreviews,
            isProfileInfoExpanded = vm_state.isProfileInfoExpanded
        )
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
                        authorizationRepository.logOut()
                        authorizationRepository.setAuthState(AppState.Unauthorized)
                    }
                }

                MovieEvents.ProfilePressed -> {
                    viewModelScope.launch {
                        _vm_state.value = vm_state.value.copy(user = authorizationRepository.getUserInfo() ?: User())
                        _vm_state.value = vm_state.value.copy(isProfileInfoExpanded = !vm_state.value.isProfileInfoExpanded)
                    }
                }
            }
        }

}