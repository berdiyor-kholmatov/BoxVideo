package com.example.boxvideo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.boxvideo.ui.theme.BoxVideoTheme
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxVideoTheme {
                    PlayerScreen()

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

    Column()  {
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
        val imageLoader = remember {
            ImageLoader.Builder(context)
                .okHttpClient {
                    OkHttpClient.Builder()
                        .addInterceptor { chain ->
                            val request = chain.request().newBuilder()
                                .header("User-Agent", "Mozilla/5.0")
                                .build()
                            chain.proceed(request)
                        }
                        .build()
                }
                .build()
        }

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data("https://asilmedia.org/uploads/mini/fullstory/ed/8bd5c566809505f3363b226e6bee64.webp")
                .build(),
            imageLoader = imageLoader,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            contentScale = ContentScale.Crop,
            onError = {
                println("ERROR OF IMAGE ASYNC IMAGE: ${it.result.throwable}")
            }
        )
    }
}