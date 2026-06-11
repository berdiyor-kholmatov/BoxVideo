package com.example.boxvideo.network.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse (
    val token: String,
)