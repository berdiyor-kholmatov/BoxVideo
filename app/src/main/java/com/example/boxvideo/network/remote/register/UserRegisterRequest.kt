package com.example.boxvideo.network.remote.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val login: String,
    val email: String,
    val password: String,
)