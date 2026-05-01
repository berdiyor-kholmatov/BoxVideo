package com.example.boxvideo.ui.player

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.repository.VideoRepository
import com.example.boxvideo.ui.movieDetail.DetailViewModel
import com.example.boxvideo.ui.movieList.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoId = intent.getIntExtra("videoId", 0)

        enableEdgeToEdge()
        setContent {
            val playerViewModel: PlayerViewModel = hiltViewModel()
            playerViewModel.getPlayerReady(videoId)
            PlayerScreen(playerViewModel.player)
        }
    }
}


@Composable
fun PlayerScreen(player: ExoPlayer) {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        factory = {
            PlayerView(it).apply {
                this.player = player
            }
        }
    )
}