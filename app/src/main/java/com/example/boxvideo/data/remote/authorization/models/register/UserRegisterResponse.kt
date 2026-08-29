package com.example.boxvideo.data.remote.authorization.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse (
    val token: String,
)