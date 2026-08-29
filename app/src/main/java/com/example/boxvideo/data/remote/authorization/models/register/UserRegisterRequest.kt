package com.example.boxvideo.data.remote.authorization.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val login: String,
    val email: String,
    val password: String,
)