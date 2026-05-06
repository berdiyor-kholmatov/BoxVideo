package com.example.boxvideo.ui.player

import android.app.PictureInPictureParams
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
import kotlin.text.toFloat

@AndroidEntryPoint
class PlayerActivity: ComponentActivity() {

    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val videoId = intent.getIntExtra("videoId", 0)

        enableEdgeToEdge()
        setContent {
            val playerViewModel: PlayerViewModel = hiltViewModel()
//            playerViewModel.getPlayerReady(videoId)

            PlayerScreen(playerViewModel.player, playerViewModel)
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    private fun enterPiPMode() {
        val params = PictureInPictureParams.Builder()
            .setAspectRatio(Rational(16, 9))
            .build()
        enterPictureInPictureMode(params)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        enterPiPMode()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PlayerActivity", "onDestroy, isFinishing=$isFinishing")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PlayerActivity", "onStop")
        playerViewModel.player.release()
    }

    override fun onPause() {
        super.onPause()
        Log.d("PlayerActivity", "onPause, isInPiP=${isInPictureInPictureMode}")
    }
}


@Composable
fun PlayerScreen(player: ExoPlayer, playerViewModel: PlayerViewModel) {
    val state by playerViewModel.state.collectAsState()
    Box(
        contentAlignment = androidx.compose.ui.Alignment.BottomEnd,
    ) {

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            factory = {
                PlayerView(it).apply {
                    this.player = player
                    useController = false
                }
            }
        )

        MusicProgressBar(
            progress = state.currentPosition.toFloat() / state.videoDuration.toFloat(),
            onSeek = { newProgress ->
                val newPosition = ((state.videoDuration.toFloat()) * newProgress).toLong()
                player.seekTo(newPosition)
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
    }
}



@Composable
fun MusicProgressBar(
    progress: Float, // 0f..1f
    onSeek: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp) // 👈 ВОТ ТВОЙ КОНТРОЛЬ ТОЛЩИНЫ
            .clip(RoundedCornerShape(50))
            .background(Color.Gray.copy(alpha = 0.3f))
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val newProgress = offset.x / size.width
                    onSeek(newProgress.coerceIn(0f, 1f))
                }
            },
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(Color.White)
        )
    }
}