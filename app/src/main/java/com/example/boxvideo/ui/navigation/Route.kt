package com.example.boxvideo.ui.navigation

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random

sealed interface Route {

    data class Login @OptIn(ExperimentalUuidApi::class) constructor(
        val id: String = random().toString()
    ): Route

    object Register: Route

    object Home: Route

    data class Details(val id: Int): Route
}