package com.example.boxvideo.data.remote.authorization.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    val token: String
)