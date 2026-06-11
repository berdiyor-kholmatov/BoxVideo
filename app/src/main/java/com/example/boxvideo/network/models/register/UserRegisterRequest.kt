package com.example.boxvideo.network.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val login: String,
    val email: String,
    val password: String,
)