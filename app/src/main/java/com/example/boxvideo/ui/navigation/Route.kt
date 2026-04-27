package com.example.boxvideo.ui.navigation

import androidx.navigation3.runtime.NavKey



sealed interface Route {

    object Home: Route

    data class Details(val id: Int): Route
}