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
fun NavigationRoot(){
//    val rootBackStack: MutableList<Any> = remember { mutableStateListOf(destination) }
    val rootBackStack = remember {
        mutableStateListOf<Route>(Route.Home)
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
                else -> throw IllegalArgumentException("Unknown route: $key")
            }
        }
    )
}