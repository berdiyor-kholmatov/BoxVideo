package com.example.boxvideo.network.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val login: String,
    val password: String
)