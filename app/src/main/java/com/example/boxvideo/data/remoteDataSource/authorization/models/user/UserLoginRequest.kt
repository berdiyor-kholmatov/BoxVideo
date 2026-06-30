package com.example.boxvideo.data.remoteDataSource.authorization.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRequest(
    val login: String,
    val password: String
)