package com.example.boxvideo.ui.authorization.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.ActivityViewModel
import com.example.boxvideo.repository.networkRepo.NetworkRepository
import com.example.boxvideo.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val networkRepository: NetworkRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()


    fun onEvent(event: LoginEvents): Unit {
        when(event) {
            is LoginEvents.UsernameInput -> {
                _state.update { currentState ->
                    currentState.copy(username = event.username)
                }
            }
            is LoginEvents.PasswordInput -> {
                _state.update { currentState ->
                    currentState.copy(password = event.password)
                }
            }
            is LoginEvents.LoginPressed -> {

                if(_state.value.password.isEmpty() || _state.value.username.isEmpty()) return
                viewModelScope.launch {
                    if (networkRepository.login(_state.value.username, _state.value.password))
                        sessionManager.authorize()
                    else
                        _state.update { currentState ->
                            currentState.copy(error = true)
                        }
                }
            }
        }
    }


}