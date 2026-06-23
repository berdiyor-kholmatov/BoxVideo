package com.example.boxvideo.ui.navigation

import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

//sealed interface Route {
//
//    object Login : Route
//
//    object Register: Route
//
//    object Home: Route
//
//    data class Details(val id: Int): Route
//}

sealed interface Route {

    data object Auth: Route {
        object Login: Route

        object Register: Route
    }

    data object Main: Route {
        object Home: Route

        data class Details(val id: Int): Route
    }
}