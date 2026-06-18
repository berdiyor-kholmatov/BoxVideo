package com.example.boxvideo.ui.authorization.register

sealed class RegisterEvents {
    data class UsernameInput(val username: String): RegisterEvents()
    data class EmailInput(val email: String): RegisterEvents()
    data class PasswordInput(val password: String): RegisterEvents()
    data class PasswordConfirmationInput(val passwordConfirmation: String): RegisterEvents()
    object RegisterPressed: RegisterEvents()
    object PasswordSwitchVisibility: RegisterEvents()
    object PasswordConfirmationSwitchVisibility: RegisterEvents()
}