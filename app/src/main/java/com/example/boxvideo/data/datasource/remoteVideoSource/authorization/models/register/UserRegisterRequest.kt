package com.example.boxvideo.data.datasource.remoteVideoSource.authorization.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterRequest(
    val login: String,
    val email: String,
    val password: String,
)