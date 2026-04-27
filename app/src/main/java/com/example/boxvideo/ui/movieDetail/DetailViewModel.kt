package com.example.boxvideo.ui.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.repository.VideoRepository
import com.example.boxvideo.ui.movieList.MovieState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel(assistedFactory = DetailViewModel.Factory::class)
class DetailViewModel @AssistedInject constructor(
    private  val repository: VideoRepository,
    @Assisted
    private val movieId: Int
) : ViewModel() {

    val state = repository.getVideoFileById(movieId)
        .map { videoFile ->
            DetailState(video = videoFile)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailState()
        )

    fun onEvent(event: DetailEvents) {

    }


    @AssistedFactory
    interface Factory {
        fun create(movieId: Int): DetailViewModel
    }
}