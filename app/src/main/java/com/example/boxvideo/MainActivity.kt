package com.example.boxvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.boxvideo.ui.theme.BoxVideoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxVideoTheme {
                Box(
                    modifier = Modifier
                        .aspectRatio(16/9f),
                    contentAlignment = Alignment.Center
                ) {
                    PlayerScreen()
                }

            }
        }
    }
}


@Composable
fun PlayerScreen() {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    val currentMovie = "https://fayllar1.ru/33/kinolar/Leo%202023%20480p%20(asilmedia.net).mp4"

    LaunchedEffect(currentMovie) {
        val mediaItem = MediaItem.fromUri(currentMovie)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                this.player = player
            }
        }
    )
}