package com.example.boxvideo.ui.authorization.login

sealed class LoginEvents {
    data class UsernameInput(val username: String): LoginEvents()
    data class PasswordInput(val password: String): LoginEvents()
    data class LoginPressed(val onSuccess: () -> Unit): LoginEvents()
}