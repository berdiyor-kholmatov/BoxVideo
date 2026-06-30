package com.example.boxvideo.data.remoteDataSource.authorization.models.register

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResponse (
    val token: String,
)