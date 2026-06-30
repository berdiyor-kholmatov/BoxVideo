package com.example.boxvideo.data.remoteDataSource.authorization.models.user

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResponse(
    val token: String
)