package com.example.boxvideo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.boxvideo.AppState
import kotlin.collections.listOf

@Composable
fun RootNavigation(appState: AppState){

    val rootBackStack = remember {
        mutableStateListOf<Route>(
            if (appState == AppState.Authorized) Route.Home else Route.Login
        )
    }


    LaunchedEffect(appState) {
        when(appState) {

            AppState.Unauthorized -> {
                rootBackStack.add(0, Route.Login)
                rootBackStack.removeRange(1, rootBackStack.size)
            }

            AppState.Authorized -> {
                rootBackStack.add(0, Route.Home)
                rootBackStack.removeRange(1, rootBackStack.size)
            }
            else -> {

            }
        }
    }

    NavDisplay(
        backStack = rootBackStack,
        onBack = { rootBackStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is Route.Login -> NavEntry(key) {
                    LoginNavigation()
                }

                is Route.Home -> NavEntry(key) {
                    HomeNavigation()
                }
                else -> throw IllegalArgumentException("Unknown route: $key")
            }
        }
    )
}
