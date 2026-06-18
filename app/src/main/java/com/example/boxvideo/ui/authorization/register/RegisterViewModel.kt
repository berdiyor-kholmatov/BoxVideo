package com.example.boxvideo.ui.authorization.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.AppState
import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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
                    _state.value.copy(email = event.email)
                }
            }

            is RegisterEvents.PasswordInput -> {
                _state.update {
                    _state.value.copy(password = event.password)
                }
            }

            is RegisterEvents.PasswordConfirmationInput -> {
                _state.update {
                    _state.value.copy( passwordConfirmation = event.passwordConfirmation)
                }
            }

            is RegisterEvents.RegisterPressed -> {

                if(!isInputsCorrect()) _state.update {
                    _state.value.copy(error =  "Wrong input! Please, check the input fields.")
                }
                viewModelScope.launch {
                    if( authorizationRepository.register(
                        login = _state.value.username,
                        email = _state.value.email,
                        password = _state.value.password
                    )) {
                        authorizationRepository.setAuthState(AppState.Authorized)
                    } else {
                        _state.update {
                            _state.value.copy(error =  "Authorization failure, please repeat later.")
                        }
                    }
                }
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

    private fun isInputsCorrect(): Boolean {
        val isUsernameCorrect = _state.value.username.isEmpty()
        val isEmailCorrect = !_state.value.email.isEmpty()
        val isPasswordCorrect = !_state.value.password.isEmpty()
        val isPasswordAndConfirmationSame = _state.value.password == _state.value.passwordConfirmation

        return isUsernameCorrect || isEmailCorrect || isPasswordCorrect || isPasswordAndConfirmationSame
    }

}