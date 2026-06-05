package com.example.boxvideo.ui.authorization.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()


    fun onEvent(event: LoginEvents): Unit {
        when(event) {
            is LoginEvents.UsernameInput -> {
                _state.update { currentState ->
                    currentState.copy(username = event.username)
                }
            }
        }
    }


}