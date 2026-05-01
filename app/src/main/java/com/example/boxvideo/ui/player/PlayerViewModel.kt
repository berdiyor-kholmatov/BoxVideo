package com.example.boxvideo.ui.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.boxvideo.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: VideoRepository,
): ViewModel() {

    val player = ExoPlayer.Builder(context).build()

    fun getPlayerReady(movieId: Int) {
        viewModelScope.launch {
            val video = repository.getVideoFileById(movieId)
            video.collectLatest { videoFile ->
                val currentMovie = videoFile?.sources?.firstOrNull()?.url ?: ""
                val mediaItem = MediaItem.fromUri(currentMovie)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}