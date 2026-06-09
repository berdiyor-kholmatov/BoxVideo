package com.example.boxvideo.network.remote.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val login: String,
    val password: String
)