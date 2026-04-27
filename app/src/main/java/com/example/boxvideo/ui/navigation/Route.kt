package com.example.boxvideo.ui.navigation

import androidx.navigation3.runtime.NavKey

sealed interface Route: NavKey {
    object Home: Route, NavKey
    data class Details(val id: Int): Route, NavKey
}