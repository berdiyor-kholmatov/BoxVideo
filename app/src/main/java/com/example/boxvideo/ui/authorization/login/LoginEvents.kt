package com.example.boxvideo.ui.authorization.login

sealed class LoginEvents {
    data class UsernameInput(val username: String): LoginEvents()
}