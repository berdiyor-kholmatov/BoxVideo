package com.example.boxvideo.ui.authorization.login

import android.R

data class LoginState (
    val username: String = "",
    val password: String = "",
    val isPasswordHidden: Boolean = true,
    val isLoading: Boolean = false,
    val error: Boolean = false
)