package com.example.boxvideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boxvideo.repository.authorizationRepo.AuthorizationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
): ViewModel() {
    val appState: StateFlow<AppState> = authorizationRepository.observeAuthState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppState.Loading
        )

    init {
        viewModelScope.launch {
            if(authorizationRepository.checkAuth())
                authorizationRepository.setAuthState(AppState.Authorized)
            else
                authorizationRepository.setAuthState(AppState.Unauthorized)
        }
    }
}