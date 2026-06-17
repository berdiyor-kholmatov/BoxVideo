package com.example.boxvideo.ui.authorization.register

import androidx.lifecycle.ViewModel
import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvents){
        when(event){
            is RegisterEvents.UsernameInput -> {
                _state.update {
                    _state.value.copy(username = event.username)
                }
            }

            is RegisterEvents.EmailInput -> {
                _state.update {
                    _state.value.copy(username = event.email)
                }
            }

            is RegisterEvents.PasswordInput -> {
                _state.update {
                    _state.value.copy(username = event.password)
                }
            }

            is RegisterEvents.RegisterPressed -> {
//                authorizationRepository
            }

            is RegisterEvents.PasswordSwitchVisibility -> {
                _state.update {
                    _state.value.copy(isPasswordHidden = !_state.value.isPasswordHidden)
                }
            }

            is RegisterEvents.PasswordConfirmationSwitchVisibility -> {
                _state.update {
                    _state.value.copy(isPasswordConfirmationHidden = !_state.value.isPasswordConfirmationHidden)
                }
            }

        }
    }
}