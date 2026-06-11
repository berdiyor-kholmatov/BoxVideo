package com.example.boxvideo.network.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    val token: String
)