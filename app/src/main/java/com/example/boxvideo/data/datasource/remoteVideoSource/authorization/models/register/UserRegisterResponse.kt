package com.example.boxvideo.data.datasource.remoteVideoSource.authorization.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse (
    val token: String,
)