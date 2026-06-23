package com.example.boxvideo.data.datasource.remoteVideoSource.authorization.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val login: String,
    val password: String
)