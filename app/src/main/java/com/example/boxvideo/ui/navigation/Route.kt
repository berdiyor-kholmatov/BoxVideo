package com.example.boxvideo.ui.navigation

sealed interface Route {

    object Login: Route

    object Register: Route

    object Home: Route

    data class Details(val id: Int): Route
}