package com.example.boxvideo.data.remote.authorization.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val login: String,
    val password: String
)