package com.example.boxvideo.network.remote.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse (
    val token: String,
)