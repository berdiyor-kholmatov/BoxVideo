package com.example.boxvideo.ui.authorization.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.boxvideo.ui.authorization.login.Login
import com.example.boxvideo.ui.authorization.login.LoginViewModel
import com.example.boxvideo.ui.authorization.register.Register
import com.example.boxvideo.ui.authorization.register.RegisterViewModel
import com.example.boxvideo.ui.navigation.Route

@Composable
fun AuthorizationNavigation()
{

    DisposableEffect(Unit) {
        Log.d("LOGIN_NAV", "entered composition")
        onDispose {
            Log.d("LOGIN_NAV", "left composition")
        }
    }

    val loginBackStack = remember {
        mutableStateListOf<Route>(Route.Auth.Login)
    }

    NavDisplay(
        backStack = loginBackStack,
        onBack = { loginBackStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Route.Auth.Login -> NavEntry(key) {

                    val owner = LocalViewModelStoreOwner.current
                    Log.d("VM_STORE", "owner: ${owner.hashCode()}, class: ${owner?.javaClass?.simpleName}")

                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val state by loginViewModel.state.collectAsState()

                    Login(
                        state = state,
                        onEvent = loginViewModel::onEvent,
                        onRegister = {
                            loginBackStack.add(0, Route.Auth.Register)
                            loginBackStack.removeRange(1, loginBackStack.size)
                        }
                    )
                }

                is Route.Auth.Register -> NavEntry(key) {
                    val registerViewModel: RegisterViewModel = hiltViewModel()
                    val state by registerViewModel.state.collectAsState()
                    Register(
                        state = state,
                        onEvent = registerViewModel::onEvent,
                        onLogin = {
                            loginBackStack.add(0, Route.Auth.Login)
                            loginBackStack.removeRange(1, loginBackStack.size)
                        }
                    )
                }
                else -> throw IllegalArgumentException("Unknown route: $key")
            }
        }
    )
}