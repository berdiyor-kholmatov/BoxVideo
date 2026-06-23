package com.example.boxvideo.ui.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.boxvideo.ui.main.movieDetail.Detail
import com.example.boxvideo.ui.main.movieDetail.DetailViewModel
import com.example.boxvideo.ui.main.movieList.MovieList
import com.example.boxvideo.ui.main.movieList.MovieViewModel
import com.example.boxvideo.ui.navigation.Route
import kotlin.uuid.ExperimentalUuidApi


@OptIn(ExperimentalUuidApi::class)
@Composable
fun MainNavigation(){
//    val rootBackStack: MutableList<Any> = remember { mutableStateListOf(destination) }
    val rootBackStack = remember {
        mutableStateListOf<Route>(Route.Main.Home)
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
                Route.Main.Home -> NavEntry(key) {
                    val movieList: MovieViewModel = hiltViewModel()
                    val state by movieList.state.collectAsState()
                    MovieList(
                        state,
                        movieList::onEvent,
                        onClick = { rootBackStack.add(Route.Main.Details(it))}
                    )
                }

                is Route.Main.Details -> NavEntry(key) {
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