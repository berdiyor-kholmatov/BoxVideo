package com.example.boxvideo.ui.authorization.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val passwordConfirmation: String = "",
    val isPasswordHidden: Boolean = true,
    val isPasswordConfirmationHidden: Boolean = true,
    val isLoading: Boolean = false,
    val error: String = ""
)