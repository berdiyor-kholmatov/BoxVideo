package com.example.boxvideo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boxvideo.domain.model.VideoFile
import com.example.boxvideo.repository.VideoRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import okhttp3.OkHttpClient
import javax.inject.Inject

@AndroidEntryPoint
class PlayerActivity: ComponentActivity() {
    @Inject
    lateinit var repository: VideoRepository
    lateinit var player: ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        val videoId = intent.getIntExtra("videoId", 0)
        enableEdgeToEdge()
        setContent {

            val video by repository.getVideoFileById(videoId).collectAsState(initial = null)

            val context = LocalContext.current
            player = remember { ExoPlayer.Builder(context).build() }
            Log.d("video", "Video: title: ${video?.title} , thumbnailUrl: ${video?.thumbnailUrl} , sources: ${video?.sources}")

            val currentMovie = video?.sources?.firstOrNull()?.url ?: ""

            Log.d("currentMovie", "currentMovie: $currentMovie")

            LaunchedEffect(currentMovie) {
                if (currentMovie == "") return@LaunchedEffect
                val mediaItem = MediaItem.fromUri(currentMovie)
                player.setMediaItem(mediaItem)
                player.prepare()
                player.play()
            }
//            DisposableEffect(player) {
//                onDispose {
//                    player.release()
//                }
//            }

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
    }

    override fun onPause() {
        super.onPause()
        Log.d("PlayerActivity", "onPause")
        player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerActivity", "onDestroy")
        player.release()
    }
}


@Composable
fun PlayerScreen(video: VideoFile?) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    val currentMovie = video?.sources?.get(0)?.url ?: ""

    LaunchedEffect(currentMovie) {
        val mediaItem = MediaItem.fromUri(currentMovie)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

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