package com.example.boxvideo.repository

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.media3.exoplayer.ExoPlayer
import com.example.boxvideo.service.VideoService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoServiceAndVideoPlayerUiBinder @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var videoService: VideoService? = null

    private var player: ExoPlayer? = null

    val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            val binder = service as VideoService.PlayerBinder
            videoService = binder.getService()
            player = videoService?.getPlayer()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            videoService = null
        }
    }

    init {
        val intent = Intent(context, VideoService::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun getPlayer(): ExoPlayer? = player
}