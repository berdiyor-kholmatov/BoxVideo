package com.example.boxvideo.ui.main.movieList

import com.example.boxvideo.ui.player.PlayerEvents

sealed class MovieEvents {
    object LogOut: MovieEvents()
    object ProfilePressed: MovieEvents()
}