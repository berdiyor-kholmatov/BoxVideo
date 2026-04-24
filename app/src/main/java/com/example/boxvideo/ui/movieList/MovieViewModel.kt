package com.example.boxvideo.ui.movieList

import androidx.compose.runtime.mutableStateOf
import com.example.boxvideo.data.remote.VideoApi
import com.example.boxvideo.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MovieViewModel @Inject constructor (
    val repository: VideoRepository,
) {
    private val _state = MutableStateFlow(MovieState())

    val state: StateFlow<MovieState> = _state.asStateFlow()




    fun onEvent(event: MovieEvents) {

    }
}