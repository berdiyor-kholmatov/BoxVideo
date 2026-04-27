package com.example.boxvideo.ui.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.boxvideo.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    val repository: VideoRepository,
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

    fun onEvent(event: MovieEvents) {

    }
}