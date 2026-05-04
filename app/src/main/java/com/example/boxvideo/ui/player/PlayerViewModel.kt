package com.example.boxvideo.ui.player

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: VideoRepository,
): ViewModel() {

    val player = ExoPlayer.Builder(context).build()
    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    private var progressJob: Job? = null
    private var currentUrl: String? = null

    init {
        player.addListener ( object: Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _state.value = _state.value.copy(isPlaying = isPlaying)
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    // Теперь duration точно доступен
                    _state.value = _state.value.copy(
                        videoDuration = player.duration
                    )
                    startTrackingProgress()
                }
            }
        })
    }

    fun getPlayerReady(movieId: Int) {
        viewModelScope.launch {
            val video = repository.getVideoFileById(movieId)
            video.collectLatest { videoFile ->
                startPlayer(videoFile)
                _state.value = PlayerState(
                    videoFile = videoFile,
                )
            }
        }
    }

    private fun startTrackingProgress(){
        progressJob?.cancel()

        progressJob = viewModelScope.launch {
            // ExoPlayer требует обращения с того же looper'а, что и был создан (обычно main).
            withContext(Dispatchers.Main.immediate) {
                //(playerState.value.selectedMusic?.duration ?: 0) >= player.currentPosition) it have to be checked because it won't stop without it
                while (isActive && (_state.value.videoDuration) >= player.currentPosition) {
                    Log.d("PlayerPositionTracker", "Tracking progress ${player.currentPosition}, ${_state.value.videoDuration}")
                    _state.value = _state.value.copy(
                        currentPosition = player.currentPosition
                    )
                    delay(1000)
                }
            }
        }
    }

    fun stopTrackingProgress() {
        progressJob?.cancel()
    }

    private fun startPlayer(videoFile: VideoFile?){
        val url = videoFile?.sources?.firstOrNull()?.url ?: return
        if (currentUrl == url) return
        currentUrl = url
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onCleared() {
        super.onCleared()
        player.release()
    }

}