package com.example.boxvideo.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class VideoService: Service() {

    private var  player: ExoPlayer? = null

    private val binder by lazy { PlayerBinder() }

    override fun onBind(intent: Intent?): IBinder = binder

    inner class PlayerBinder: Binder() {
        fun getService(): VideoService {
            return this@VideoService
        }
    }




    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {




        return super.onStartCommand(intent, flags, startId)
    }


    fun getPlayer(): ExoPlayer? = player

    fun play ( url: String ) {
        if (player == null) {
            player = ExoPlayer.Builder(this).build()
        }
        val mediaItem = MediaItem.fromUri(url)
        player?.apply {
            setMediaItem(mediaItem)
            prepare()
            play()
        }
    }



}