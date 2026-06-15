package com.example.boxvideo.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.boxvideo.ui.authorization.register.RegisterViewModel
import com.example.boxvideo.ui.movieDetail.Detail
import com.example.boxvideo.ui.movieDetail.DetailViewModel
import com.example.boxvideo.ui.movieList.MovieList
import com.example.boxvideo.ui.movieList.MovieViewModel
import kotlin.collections.MutableList
import com.example.boxvideo.ui.authorization.register.Register
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid.Companion.random


@OptIn(ExperimentalUuidApi::class)
@Composable
fun NavigationRoot(destination: Route = Route.Login()){
//    val rootBackStack: MutableList<Any> = remember { mutableStateListOf(destination) }

    val rootBackStack = remember(destination) {

        mutableStateListOf(destination)

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
                Route.Home -> NavEntry(key) {
                    val movieList: MovieViewModel = hiltViewModel()
                    val state by movieList.state.collectAsState()
                    MovieList(
                        state,
                        movieList::onEvent,
                        onClick = { rootBackStack.add(Route.Details(it))}
                    )
                }

                is Route.Details -> NavEntry(key) {
                    val detailViewModel: DetailViewModel =
                        hiltViewModel<DetailViewModel, DetailViewModel.Factory>(
                            creationCallback = { factory ->
                                factory.create(movieId = key.id)
                            },
                            key = key.id.toString()
                        )
                    val state by detailViewModel.state.collectAsState()
                    Detail(
                        state,
                        detailViewModel::onEvent,
                        onClick = {}
                    )
                }

                is Route.Login -> NavEntry(key){

                    val vmStoreOwner = LocalViewModelStoreOwner.current

                    LaunchedEffect(Unit) {
                        Log.d("VM_STORE_NAVIGATION", "owner: ${vmStoreOwner.hashCode()}")
                        Log.d("VM_STORE_NAVIGATION", "store: ${vmStoreOwner?.viewModelStore.hashCode()}")
//                        Log.d("VM_STORE", "store: ${vmStoreOwner?.viewModelStore?.clear()}")
                    }

                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val state by loginViewModel.state.collectAsState()

                    Login(
                        state = state,
                        onEvent = loginViewModel::onEvent,
                        onRegister = {
                            rootBackStack.removeLastOrNull()
                            rootBackStack.add(Route.Register)
                        }
                    )
                }

                is Route.Register -> NavEntry(key){
                    val registerViewModel: RegisterViewModel = hiltViewModel()
                    val state by registerViewModel.state.collectAsState()
                    Register(state, registerViewModel::onEvent)
                }

                else -> throw IllegalArgumentException("Unknown route: $key")
            }
        }
    )
}